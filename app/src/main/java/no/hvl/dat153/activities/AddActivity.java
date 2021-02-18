package no.hvl.dat153.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import no.hvl.dat153.R;
import no.hvl.dat153.classes.PersonDatabase;
import no.hvl.dat153.classes.Person;

public class AddActivity extends MenuActivity {
    EditText name;
    ImageView image;

    private PersonDatabase db;

    public int students;

    // Request codes
    static final int REQUEST_CAMERA_CODE = 101;
    static final int CAMERA_REQUEST_CODE = 102;
    static final int REQUEST_GALLERY_CODE = 103;
    static final int GALLERY_REQUEST_CODE = 104;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // Get database
        db = PersonDatabase.getInstance(this);
        students = db.personDao().getDb().size();

        // Get views
        name = findViewById(R.id.nameTextView);
        image = findViewById(R.id.imageView);
        Button takePhoto = findViewById(R.id.button5);
        takePhoto.setOnClickListener(v -> askCameraPermission()); // Listener on click camera
        Button chooseExisting = findViewById(R.id.button6);
        chooseExisting.setOnClickListener(v -> askGalleryPermission()); // Listener on click existing image
    }

    public void askCameraPermission() { // Ask for camera permission
        name.onEditorAction(EditorInfo.IME_ACTION_DONE); // Hide keyboard

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
        } else {
            openCamera();
        }
    }

    public void openCamera() { // Open camera
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

    public void askGalleryPermission() { // Ask for gallery permission
        name.onEditorAction(EditorInfo.IME_ACTION_DONE); // Hide keyboard

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_GALLERY_CODE);
        } else {
            openGallery();
        }
    }

    public void openGallery() { // Open gallery
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public void addNewStudent(View view) { // Add new student to the database
        if (!name.toString().equals("") && image.getDrawable() != null) {
            db.personDao().addStudent(new Person(image.getDrawable(), name.getText().toString()));
            students += 1;
            Toast.makeText(this, "New student added", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, DatabaseActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CAMERA_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission is required to use the camera.", Toast.LENGTH_SHORT).show();
            }
        }

        if (requestCode == REQUEST_GALLERY_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission is required choose existing image.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            // Set imageview to the newly captured image
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bitmap);
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            // Set imageview to the selected image
            image.setImageURI(data.getData());
        }
    }
}