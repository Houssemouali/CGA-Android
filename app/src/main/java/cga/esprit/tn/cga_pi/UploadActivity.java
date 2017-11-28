package cga.esprit.tn.cga_pi;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.File;

import java.io.IOException;

import cga.esprit.tn.cga_pi.R;
import helper.ImageInputHelper;
import helper.RealPathUtil;



/**
 * Created by cliqers on 1/7/2016.
 */
public class UploadActivity extends ActionBarActivity implements ImageInputHelper.ImageActionListener {

    private static final String TAG = UploadActivity.class.getSimpleName();
    public static ImageView imageProfile;

    public static Bitmap bitmap;


    private ImageInputHelper imageInputHelper;
    private static final int REQUEST_CAMERA = 1001;
    private static final int REQUEST_CAMERA_PERMISSION = 1;
    private static final int REQUEST_STORAGE_PERMISSION = 2;
    private static final int REQUEST_GALLERY = 1002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        ((Button) findViewById(R.id.ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UploadActivity.this, Profile.class));
            }
        });

        ((Button) findViewById(R.id.annuler)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap=null;
                startActivity(new Intent(UploadActivity.this, Profile.class));
            }
        });


        imageInputHelper = new ImageInputHelper(this);
        imageInputHelper.setImageActionListener(this);

        findViewById(R.id.select_photo_from_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT < 23) {
                    imageInputHelper.selectImageFromGallery();
                } else {
                    initGalleryPermission();
                }
            }
        });


    }



    @TargetApi(Build.VERSION_CODES.M)
    private void initGalleryPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "Permission to read Storage", Toast.LENGTH_SHORT).show();
            }
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        } else {
            imageInputHelper.selectImageFromGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageInputHelper.takePhotoWithCamera();
            } else {
                Toast.makeText(this, "Permission denied by user", Toast.LENGTH_SHORT).show();
            }
        } else if(requestCode == REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length == 1 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                imageInputHelper.selectImageFromGallery();
            } else {
                Toast.makeText(this, "Permission denied by user", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imageInputHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onImageSelectedFromGallery(Uri uri, File imageFile) {
        // cropping the selected image. crop intent will have aspect ratio 16/9 and result image
        // will have size 800x450
//        imageInputHelper.requestCropImage(uri, 800, 450, 16, 9);
        displayImage(uri);
    }

    @Override
    public void onImageTakenFromCamera(Uri uri, File imageFile) {
        // cropping the taken photo. crop intent will have aspect ratio 16/9 and result image
        // will have size 800x450
//        imageInputHelper.requestCropImage(uri, 800, 450, 16, 9);
        displayImage(uri);
    }

    @Override
    public void onImageCropped(Uri uri, File imageFile) {
        displayImage(uri);
    }

    private void displayImage(Uri uri) {
        try {
             bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            String realPath = "";

            if (Build.VERSION.SDK_INT < 11) {
                realPath = RealPathUtil.getRealPathFromURI_BelowAPI11(this, uri);
            } else if (Build.VERSION.SDK_INT < 19) {
                realPath = RealPathUtil.getRealPathFromURI_API11to18(this, uri);
            } else {
                realPath = RealPathUtil.getRealPathFromURI_API19(this, uri);
            }

            // showing bitmap in image view
            ((ImageView) findViewById(R.id.image)).setImageBitmap(bitmap);

            Log.d(TAG, "realPath: " + realPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}