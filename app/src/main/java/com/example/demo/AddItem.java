package com.example.demo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class AddItem extends AppCompatActivity {
//    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private StorageReference reference;
    private ProgressBar mProgress;
    private TextView pin;
    private ImageView itemImage;
    private TextView description ;
    private TextView title;
    private TextView availability;
    private String Description,Title, UserId,Pin,Availabilty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("items").push();
        reference = FirebaseStorage.getInstance().getReference();
        pin = findViewById(R.id.pin);
        itemImage = findViewById(R.id.productimage);
        description = findViewById(R.id.description);
        title = findViewById(R.id.title);
        availability = findViewById(R.id.availibility);

        mProgress = findViewById(R.id.progressBar2);
    }
    public void addItem(final View view){

        Pin = pin.getText().toString();
        Description = description.getText().toString();
        Title = title.getText().toString();
//        UserId = mAuth.getUid();
        Availabilty = availability.getText().toString();

        if(targetUri==null){
            Toast.makeText(getApplicationContext(), "Please select an image", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Title)) {
            Toast.makeText(getApplicationContext(), "Please enter Title", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Description)) {
            Toast.makeText(getApplicationContext(), "Please enter Description", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Availabilty)) {
            Toast.makeText(getApplicationContext(), "Please enter Availability", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Pin)) {
            Toast.makeText(getApplicationContext(), "Please enter Pin", Toast.LENGTH_LONG).show();
            return;
        }
        final item item = new item(Title,"",user.getUid(),Availabilty , Pin , Description);

        StorageReference ref = reference.child("itemsPic").child(targetUri.getLastPathSegment());
        ref.putFile(targetUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        item.setImage_url(uri.toString());

                        mDatabase.setValue(item);

                        mProgress.setVisibility(View.GONE);
                        Intent intent = new Intent(AddItem.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"item uploaded successfully!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                //calculating progress percentage
                @SuppressWarnings("VisibleForTests")
                Double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                //displaying percentage in progress dialog
                mProgress.setVisibility(View.VISIBLE);
                mProgress.setProgress((int) Math.ceil(progress),true);

            }
        });
    }
    private final int PICK_IMAGE_REQUEST = 1;
    private Uri targetUri;
    public void setImg(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            targetUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), targetUri);
//                targetImage.setImageBitmap(bitmap);
                itemImage.setVisibility(View.VISIBLE);
                Picasso.with(getApplicationContext()).load(targetUri.toString()).into(itemImage);
                Log.i("targetFile: ",targetUri.getPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
