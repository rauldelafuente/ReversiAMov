package com.example.raul.demoreversi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class ProfileActivity extends Activity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView profilePhoto;
    String mCurrentPhotoPath;
    EditText name;
    TextView currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);

        profilePhoto = findViewById(R.id.profile_pic);
        name = findViewById(R.id.editName);
        currentName = findViewById(R.id.currentName);

        currentName.setText(loadName());
    }

    public void takePhoto(View v){
        dispatchTakePictureIntent();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            profilePhoto.setImageBitmap(imageBitmap);
        }
    }

    public void saveName(View v) {
        try {
            FileOutputStream fos = openFileOutput("name.txt", MODE_APPEND);
            PrintStream ps = new PrintStream(fos);
            String str = name.getText().toString();
            ps.println(str);
            fos.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public String loadName(){
        try {
            FileInputStream fis = openFileInput("name.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            line = bufferedReader.readLine();
            fis.close();
            return line;
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
