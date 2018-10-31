package com.spitslide.celebrityrecognition;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
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

public class MainActivity extends AppCompatActivity {

    private static final String PHOTO = "photo";
    private static final java.lang.String EXT = ".jpg";
    private static final int REQUEST_TAKE_PHOTO = 1;
    private Uri photoURI;
    private ImageView photoView;
    private File photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void detectFace(View view) {
        Intent intent = new Intent(this, DetectionActivity.class);
        String photoFilePath = photo.getAbsolutePath();
        intent.putExtra("photoFile", photoFilePath);
        startActivity(intent);
    }
}