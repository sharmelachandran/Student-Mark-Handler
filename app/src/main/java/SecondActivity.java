package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    TextView tv1,tv2;
    EditText id,pass;
    private ProgressDialog progressDialog;
    Faculty faculty;
    private FirebaseAuth mAuth;
   private FirebaseDatabase database;
   private DatabaseReference ref;
    public boolean isValid(String s) {
        return (!s.trim().isEmpty());
    }
    public boolean passIsValid(String s) {
        return (s.length() >= 5 && isValid(s));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mAuth = FirebaseAuth.getInstance();
        id=(EditText)findViewById(R.id.editText20);
        pass=(EditText)findViewById(R.id.editText27);
        btn=(Button)findViewById(R.id.button5);
        tv1=(TextView) findViewById(R.id.textView5);
        tv2=(TextView)findViewById(R.id.textView4);
        progressDialog=new ProgressDialog(this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        progressDialog.setMessage("Wait for a while!!!");
        progressDialog.show();
        if (TextUtils.isEmpty(id.getText().toString().trim())&&TextUtils.isEmpty(pass.getText().toString().trim())) {
            id.setError("Required");
            pass.setError("Required");
        }
        else if(TextUtils.isEmpty(id.getText().toString().trim())){
            id.setError("Enter valid mail id");
        }
        else if (TextUtils.isEmpty(pass.getText().toString().trim())) {
            pass.setError("Required");
        }else if(!passIsValid(pass.getText().toString().trim())){
            pass.setError("password is invalid");
        }
        else
        {
            database=FirebaseDatabase.getInstance();
            ref=database.getReference().child("users").child("Faculty");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        faculty = ds.getValue(Faculty.class);
                        String tem=faculty.getAge();
                        String temp=faculty.getId();
                        String temp1=faculty.getPass();
                        if (temp.equals(id.getText().toString().trim())&&temp1.equals(pass.getText().toString().trim())) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplicationContext(),FifthActivity.class);
                            startActivity(i);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),"Login failed. Invalid credentials",Toast.LENGTH_LONG).show();
                }
            });
           }

         }
       });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(getApplicationContext(),FacultyRegActivity.class);
                startActivity(j);
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k=new Intent(getApplicationContext(),forgot.class);
                startActivity(k);
            }
        });
    }
}