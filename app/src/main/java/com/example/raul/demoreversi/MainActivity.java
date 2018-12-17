package com.example.raul.demoreversi;

import android.app.Activity;
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
                Intent intent = new Intent(MainActivity.this,VsCPU.class);
                startActivity(intent);
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
