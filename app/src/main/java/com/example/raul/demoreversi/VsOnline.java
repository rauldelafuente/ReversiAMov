package com.example.raul.demoreversi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class VsOnline extends Activity {
    public static final int  SERVER = 0;
    public static final int  CLIENT = 1;

    private static final int PORT = 8899;

    int mode = SERVER;
    Handler procMsg = null;
    ProgressDialog pd = null;

    ServerSocket serverSocket=null;
    Socket socketGame = null;
    BufferedReader input;
    PrintWriter output;

    GridViewAdapter grid;
    GridView gv;
    TextView tvW;
    TextView tvB;
    Board board = new Board();
    Button btnskp, btnnp, btnskp2, btnnp2;
    String player = "b";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vs_online);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            Toast.makeText(this, R.string.error_netconn, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        Intent intent = getIntent();
        if (intent != null)
            mode = intent.getIntExtra("mode", SERVER);

        procMsg = new Handler();


        tvW = findViewById(R.id.whiteResult);
        tvB = findViewById(R.id.blackResult);

        btnskp = findViewById(R.id.btnskp);
        btnnp = findViewById(R.id.btnnp);
        btnskp2 = findViewById(R.id.btnskp2);
        btnnp2 = findViewById(R.id.btnnp2);

        grid = new GridViewAdapter(this, board);
        gv = findViewById(R.id.onlineBoard);

        board.setMov(player);

        gv.setAdapter(grid);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //do the move to that postion
                board.move (position);
                //Notify to the adapter that something changed
                grid.notifyDataSetChanged();
                //set the scoreboard of the activity
                tvW.setText("" + board.whiteCount());
                tvB.setText("" + board.blackCount());

                String boardString = board.getBoardAsString();
                Boolean playAgain = board.usednp1;
                Boolean skip = board.usednp1;
                final JSONObject sendObj = new JSONObject();
                try {
                    sendObj.put("board", boardString);
                    sendObj.put("playAgain", playAgain);
                    sendObj.put("skip", skip);
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Log.d("REVERSI", "Sending a move: " + sendObj);
                                output.println(sendObj);
                                output.flush();
                            } catch (Exception e) {
                                Log.d("RPS", "Error sending a move");
                            }
                        }
                    });
                    t.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mode == SERVER)
            server();
        else  // CLIENT
            clientDlg();
    }

    public void skipTurn(View view) {
    }

    public void rePlay(View view) {
    }

    private void moveOtherPlayer(JSONObject infoFromOpponent){
        try {
            String opponentBoard = infoFromOpponent.getString("board");
            Boolean opponentUsedPlayAgain = infoFromOpponent.getBoolean("playAgain");
            Boolean opponentUsedSkip = infoFromOpponent.getBoolean("skip");
            this.board.setBoardFromString("board");
            if(opponentUsedPlayAgain)
                btnnp2.setVisibility(View.INVISIBLE);
            if(opponentUsedSkip)
                btnskp2.setVisibility(View.INVISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void clientDlg() {
        final EditText edtIP = new EditText(this);
        edtIP.setText("Insert your opponent ID here");
        AlertDialog ad = new AlertDialog.Builder(this).setTitle("Reversi Client side")
                .setMessage("Server IP").setView(edtIP)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        client(edtIP.getText().toString(), PORT);
                    }
                }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        finish();
                    }
                }).create();
        ad.show();
    }

    void client(final String strIP, final int Port) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("REVERSI", "Connecting to the server  " + strIP);
                    socketGame = new Socket(strIP, Port);
                } catch (Exception e) {
                    socketGame = null;
                }
                if (socketGame == null) {
                    procMsg.post(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                    return;
                }
                commThread.start();
            }
        });
        t.start();
    }


    void server() {
        String ip = getLocalIpAddress();
        pd = new ProgressDialog(this);
        pd.setMessage(getString(R.string.server_wait_opponent_msg) + "\n(IP: " + ip
                + ")");
        pd.setTitle(R.string.serverdlg_title);
        pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
                if (serverSocket!=null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                    }
                    serverSocket=null;
                }
            }
        });
        pd.show();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(PORT);
                    socketGame = serverSocket.accept();
                    serverSocket.close();
                    serverSocket=null;
                    commThread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                    socketGame = null;
                }
                procMsg.post(new Runnable() {
                    @Override
                    public void run() {
                        pd.dismiss();
                        if (socketGame == null)
                            finish();
                    }
                });
            }
        });
        t.start();
    }

    // Communication based on JSON format with a board, and the opponent's cards

    Thread commThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                input = new BufferedReader(new InputStreamReader(
                        socketGame.getInputStream()));
                output = new PrintWriter(socketGame.getOutputStream());
                while (!Thread.currentThread().isInterrupted()) {
                    String read = input.readLine();
                    final JSONObject infoFromOpponent = new JSONObject(read);
                    Log.d("RPS", "Received: " + infoFromOpponent);
                    procMsg.post(new Runnable() {
                        @Override
                        public void run() {
                            moveOtherPlayer(infoFromOpponent);
                        }
                    });
                }
            } catch (Exception e) {
                procMsg.post(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        Toast.makeText(getApplicationContext(),
                                R.string.game_finished, Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        }
    });

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()
                            && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
