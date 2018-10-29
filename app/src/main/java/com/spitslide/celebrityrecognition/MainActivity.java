package com.spitslide.celebrityrecognition;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String PHOTO = "photo";
    private static final java.lang.String EXT = ".jpg";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private Uri photoURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takePhoto(View view) throws IOException {
        File photo = File.createTempFile(PHOTO, EXT, getExternalFilesDir(Environment.DIRECTORY_PICTURES));
        photoURI = Uri.fromFile(photo);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            findViewById(R.id.tap_here).setVisibility(View.GONE);
            ImageView photoView = findViewById(R.id.photo_view);
            photoView.setVisibility(View.VISIBLE);
            findViewById(R.id.detect_face).setVisibility(View.VISIBLE);
            findViewById(R.id.new_photo).setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(photoURI)
                    .fit()
                    .centerInside()
                    .into(photoView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Log.d("MY", "success");
                        }

                        @Override
                        public void onError(Exception e) {
                            Log.d("MY", e.toString());
                        }
                    });
        }
    }

    public void detectFace(View view) {
        Intent intent = new Intent(this, DetectionActivity.class);
        startActivity(intent);
    }
}