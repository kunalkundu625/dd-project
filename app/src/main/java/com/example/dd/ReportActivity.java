package com.example.dd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ReportActivity extends AppCompatActivity {
    private Button btnUpload, btnSave;
    private ImageView imageView;

    FirebaseStorage storage;
    StorageReference storageReference;
    private final int IMG_REQUEST_ID = 10;
    private Uri imgUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnUpload = findViewById(R.id.btn_upload);
        btnSave = findViewById(R.id.btn_save);
        imageView = findViewById(R.id.img_view);

        btnSave.setEnabled(false);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestImage();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveInfirebase();
            }
        });

    }private void requestImage(){
        Intent intent = new Intent();
        intent.setType("Image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),IMG_REQUEST_ID);
    }
    private void saveInfirebase(){
        if (imgUri != null) {
            final ProgressDialog progressDialog = new ProgressDialog (this);
            progressDialog.setTitle ("Please wait...");
            progressDialog.show ();
            StorageReference reference = storageReference.child ("picture/" + UUID.randomUUID ().toString ());
            try {

                reference.putFile (imgUri).addOnSuccessListener (new OnSuccessListener<UploadTask.TaskSnapshot> () {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss ();
                        Toast.makeText (ReportActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show ();

                    }
                })
                        .addOnFailureListener (new OnFailureListener () {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText (ReportActivity.this, "Error Occurred" + e.getMessage (), Toast.LENGTH_SHORT).show ();

                            }
                        }).addOnProgressListener (new OnProgressListener<UploadTask.TaskSnapshot> () {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress = (100.0 * snapshot.getBytesTransferred () / snapshot.getTotalByteCount ());
                        progressDialog.setMessage ("Saved" + (int) progress + "@");
                        btnUpload.setEnabled (true);
                        btnSave.setEnabled (false);

                    }
                });
            } catch (Exception e) {
                e.printStackTrace ();
            }
        }

                }
        }

        
