package com.example.demo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot extends AppCompatActivity {
     EditText passwordEmail;
     Button resetPassword;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        passwordEmail = (EditText)findViewById(R.id.editText19);
        resetPassword = (Button)findViewById(R.id.button9);
        firebaseAuth = FirebaseAuth.getInstance();
        resetPassword.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String useremail = passwordEmail.getText().toString().trim();
                 if(useremail.equals("")){
                     Toast.makeText(forgot.this, "Please enter your registered email ID", Toast.LENGTH_SHORT).show();
                 }else{
                     firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task){
                             if(task.isSuccessful()){
                                 Toast.makeText(forgot.this, "Password reset email sent!", Toast.LENGTH_SHORT).show();
                                 finish();
                                 startActivity(new Intent(forgot.this, SecondActivity.class));
                             }else{
                                 Toast.makeText(forgot.this, "Error in sending password reset email", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             }
         });
    }
}
