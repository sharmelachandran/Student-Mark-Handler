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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FourthActivity extends AppCompatActivity {
    Button btn;
    TextView tv1,tv2;
    EditText id,pass;
    private FirebaseAuth mAuth;
  private  FirebaseDatabase database;
   private DatabaseReference ref;
    Parent parent;
    private ProgressDialog progressDialog;
    public boolean isValid(String s) {
        return (!s.trim().isEmpty());
    }
    public boolean passIsValid(String s) {
        return (s.length() >= 5 && isValid(s));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        id=(EditText)findViewById(R.id.editText21);
        pass=(EditText)findViewById(R.id.editText27);
        tv1=(TextView) findViewById(R.id.textView);
        tv2=(TextView) findViewById(R.id.textView4);
        btn=(Button)findViewById(R.id.button5);
        mAuth=FirebaseAuth.getInstance();
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
                    id.setError("Enter valid mail name");
                }
                else if (TextUtils.isEmpty(pass.getText().toString().trim())) {
                    pass.setError("Required");
                }else if(!passIsValid(pass.getText().toString().trim())){
                    pass.setError("password is invalid");
                }
                else
                {
                    String mail=id.getText().toString().trim();
                    String passw=pass.getText().toString().trim();
                    mAuth.signInWithEmailAndPassword(mail,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),SixthActivity.class);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(), "Login failed. Invalid credentials", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    /*database= FirebaseDatabase.getInstance();
                    ref=database.getReference().child("users").child("Parent");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                parent= ds.getValue(Parent.class);
                                String tem=parent.getId();
                                String temp=parent.getName();
                                String temp1=parent.getPass();
                                if (temp.equals(name.getText().toString().trim())&&temp1.equals(pass.getText().toString().trim())) {
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(getApplicationContext(),SixthActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Login failed. Invalid credentials",Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });*/
                }

            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),ParentRegActivity.class);
                startActivity(i);
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
