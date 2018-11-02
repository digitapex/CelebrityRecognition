package com.spitslide.celebrityrecognition;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;
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
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void detectFace(View view) {
        Intent intent = new Intent(this, DetectionActivity.class);
        String photoFilePath = photoFile.getAbsolutePath();
        intent.putExtra("photoFile", photoFilePath);
        startActivity(intent);
    }

    public void choosePhoto(View view) {
        showFilePicker();
    }

    private void showFilePicker() {
        DialogProperties properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File("/");
        properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
        properties.offset = new File("/");
        properties.extensions = new String[]{"jpg", "png", "jpeg"};

        final FilePickerDialog dialog = new FilePickerDialog(this, properties);
        dialog.setTitle("Select a photo");
        dialog.setDialogSelectionListener(new DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                String photoPath = files[0];
                insertPhotoIntoView(photoPath);
            }
        });

        dialog.show();

        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
    }

    private void insertPhotoIntoView(String photoPath) {
        findViewById(R.id.tap_here).setVisibility(View.GONE);
        ImageView photoView = findViewById(R.id.photo_view);
        photoView.setVisibility(View.VISIBLE);
        findViewById(R.id.detect_face).setVisibility(View.VISIBLE);
        photoFile = new File(photoPath);
        Picasso.get()
                .load(photoFile)
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