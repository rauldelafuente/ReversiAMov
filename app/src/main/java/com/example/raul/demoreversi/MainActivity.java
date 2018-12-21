package com.example.raul.demoreversi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
                //Intent intent = new Intent(MainActivity.this,VsCPU.class);
                //startActivity(intent);
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
                new AlertDialog.Builder(MainActivity.this).setTitle("Choose your side")
                        .setPositiveButton("Server", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, VsOnline.class);
                                intent.putExtra("mode", VsOnline.SERVER);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("Client", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, VsOnline.class);
                                intent.putExtra("mode", VsOnline.CLIENT);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_LONG).show();
                Intent intentProfile = new Intent(this, ProfileActivity.class);
                startActivity(intentProfile);
                return true;
            case R.id.help:
                Toast.makeText(this, "Help", Toast.LENGTH_LONG).show();
                Intent intentHelp = new Intent(this, HelpActivity.class);
                startActivity(intentHelp);
                return true;
            case R.id.credits:
                Toast.makeText(this, "Credits", Toast.LENGTH_LONG).show();
                Intent intentCredits = new Intent(this, CreditsActivity.class);
                startActivity(intentCredits);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
