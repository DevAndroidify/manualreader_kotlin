package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class upload extends AppCompatActivity {
    Button edit;
    Button button;
    EditText name;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    Button retrive;
    String select;
    EditText editText;
    //Spinner spinner;
    //String[] items={"RedmiCamera","RedmiTv","Samsungphone","Samsungcamera","SamsungTv"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        editText=findViewById(R.id.companyname);



        //spinner=findViewById(R.id.spinner);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,items);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          //  @Override
            //public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // select=parent.getItemAtPosition(position).toString();
              //  Toast.makeText(upload.this, select, Toast.LENGTH_SHORT).show();
            //}

            //@Override
            //public void onNothingSelected(AdapterView<?> parent) {

            //}
      //  });
        edit=findViewById(R.id.editText);
        button=findViewById(R.id.button);
        retrive=findViewById(R.id.retrieve);
        name=findViewById(R.id.name);
        retrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrivepdfs();
            }
        });
        storageReference= FirebaseStorage.getInstance().getReference();




        button.setEnabled(false);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPDF();
            }
        });

    }



    private void selectPDF() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF FILE SELECT"),12);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==12 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            button.setEnabled(true);
            edit.setText(data.getDataString().
                    substring(data.getDataString().lastIndexOf("/")+1));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadpdffilefirebase(data.getData());
                }
            });

        }
    }

    private void uploadpdffilefirebase(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("File is loading");
        progressDialog.show();

        StorageReference reference=storageReference.child("pdfreader"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            select=editText.getText().toString();
                        databaseReference= FirebaseDatabase.getInstance().getReference(select);
                        Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isComplete());
                        Uri uri=uriTask.getResult();
                        putpdf putpdf=new putpdf(name.getText().toString(),uri.toString());
                        databaseReference.child(databaseReference.push().getKey()).setValue(putpdf);
                        Toast.makeText(upload.this, "File upload", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progress=(100.0*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        progressDialog.setMessage("File upload"+(int)progress+"%");
                    }
                });

    }
    private void retrivepdfs() {
        Intent a=new Intent(getApplicationContext(),listview.class);
        select=editText.getText().toString();
        a.putExtra("selected",select);
        startActivity(a);
    }

}