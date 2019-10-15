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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {
    TextView textTargetUri;
    private ImageView targetImage;
    private FirebaseAuth mAuth;         // user access

    private EditText email;
    private EditText password;
    private Button btn;
    private DatabaseReference mDatabase; // database reference
    private EditText Fullaname;
    private EditText phone;
    private EditText dob;
    private EditText address;
    private EditText pin;
    private final int PICK_IMAGE_REQUEST = 1;

    private StorageReference mStorageRef;    /// storage reference
    private Uri targetUri ;
    private byte[] image;
    private String url;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button buttonLoadImage = findViewById(R.id.profile_pic);
        textTargetUri = findViewById(R.id.textURL);
        targetImage = findViewById(R.id.imageDisplay);

        btn = findViewById(R.id.btn);
        buttonLoadImage.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stubIntent intent = new Intent();
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
//                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            }});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister(v);
            }
        });
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("user");

    }
    @Override
    public void onStart() {
        super.onStart();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mAuth = FirebaseAuth.getInstance();
    }
    public void onRegister(final View view) {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Fullaname = findViewById(R.id.full_name);
        phone = findViewById(R.id.phone);
        dob=findViewById(R.id.dob);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pin);
        TextView confirm = findViewById(R.id.confirmPassword);
        String confirmPassword = confirm.getText().toString();
        final String Email,Password,fullname,Dob,Address;
//        final String[] downloadurl = new String[1];
        final Long Phone,Pin;

        final ProgressBar mProgress;
        mProgress = findViewById(R.id.progressBar);

        fullname = Fullaname.getText().toString();
        Dob = dob.getText().toString();
        Address = address.getText().toString();

        Email = email.getText().toString();
        Password = password.getText().toString();
        if (targetUri==null) {
            Toast.makeText(getApplicationContext(), "Please enter select an image" +
                    "", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(fullname)) {
            Toast.makeText(getApplicationContext(), "Please enter Full name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Please enter confirm password!", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            Phone = Long.parseLong(phone.getText().toString());
            Pin = Long.parseLong(pin.getText().toString());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "Please enter PIN and Phone", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Address)) {
            Toast.makeText(getApplicationContext(), "Please enter Address!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(Dob)) {
            Toast.makeText(getApplicationContext(), "Please enter dob", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Password.equals(confirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords doesnt matches", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgress.setProgress(View.GONE);
                        if (task.isSuccessful()) {
                            if (targetUri != null) {
                                StorageReference filepath = mStorageRef.child("profilePic").child(targetUri.getLastPathSegment());
                                filepath.putBytes(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                        result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {

//                                                Log.i("image url", uri.toString());
                                                Log.i("image upload","successfull");
                                                Log.i("user", mAuth.getUid() + uri.toString()+ fullname + Dob + Phone + Pin + Address);
                                                user user = new user();
                                                user.setAddress(Address);
                                                user.setContact(Phone);
                                                user.setDob(Dob);
                                                user.setUsername(fullname);
                                                user.setPin(Pin);
                                                user.setPic_url(uri.toString());
                                                Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                                mDatabase.child(mAuth.getUid()).setValue(user);
                                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(intent);
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
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });

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
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
                image = baos.toByteArray();
                targetImage.setVisibility(View.VISIBLE);
                targetImage.setImageBitmap(bitmap);
                textTargetUri.setText(targetUri.getPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
