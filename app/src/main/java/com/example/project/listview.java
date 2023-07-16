package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class listview extends AppCompatActivity {
    ListView list;
    Button btn;
    List<putpdf> uploadedpdf;
    DatabaseReference databaseReference;
    String selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        Intent a=getIntent();
        selected=a.getStringExtra("selected");
        Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
        list=findViewById(R.id.listview);
        uploadedpdf=new ArrayList<>();
        retrievepdffiles();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                putpdf putpdf=uploadedpdf.get(position);
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setType("application/pdf");
                intent.setData(Uri.parse(putpdf.getUrl()));
                startActivity(intent);
            }
        });
      btn=findViewById(R.id.upload);
      btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(),upload.class));
          }
      });

    }

    private void retrievepdffiles() {
        databaseReference= FirebaseDatabase.getInstance().getReference(selected);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    putpdf putpdf=ds.getValue(com.example.project.putpdf.class);
                    uploadedpdf.add(putpdf);

                }
                String[] uploadsName=new String[uploadedpdf.size()];
                for(int i=0;i<uploadsName.length;i++){
                    uploadsName[i]=uploadedpdf.get(i).getName();
                }
                ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,uploadsName){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view=super.getView(position,convertView,parent);
                        TextView textView=(TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.BLACK);
                        textView.setTextSize(20);
                        return view;

                    }
                };
                list.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}