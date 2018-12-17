package com.example.raul.demoreversi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCpu = findViewById(R.id.btn1);
        Button btnLocal = findViewById(R.id.btn2);
        Button btnConn = findViewById(R.id.btn3);


        btnCpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle("Choose your side")
                        .setPositiveButton("Server", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Server", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Client", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Client", Toast.LENGTH_LONG).show();
                            }
                        }).show();
                Toast.makeText(MainActivity.this, "COMMING SOON!", Toast.LENGTH_LONG).show();
            }
        });

        btnLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,VsLocal.class);
                startActivity(intent);
            }
        });

        btnConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "COMMING SOON!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
