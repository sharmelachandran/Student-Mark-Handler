package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentRegActivity extends AppCompatActivity {
    Button btn;
    TextView tv;
    EditText name,id,phn,mail,pass;
    public boolean isValid(String s) {
        return (!s.trim().isEmpty());
    }
    public boolean passIsValid(String s) {
        return (s.length() >= 5 && isValid(s));
    }
    public int C2I(String st) {
        return Integer.parseInt(st);
    }
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);
        mAuth=FirebaseAuth.getInstance();
        id=(EditText)findViewById(R.id.editText9);
        name=(EditText)findViewById(R.id.editText10);
        phn=(EditText)findViewById(R.id.editText11);
        mail=(EditText)findViewById(R.id.editText12);
        pass=(EditText)findViewById(R.id.editText13);
        btn=(Button)findViewById(R.id.button6);
        tv=(TextView)findViewById(R.id.textView6);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _id, _name, _phn, _mail, _pass;
                _id = id.getText().toString().trim();
                _name = name.getText().toString().trim();
                _phn = phn.getText().toString().trim();
                _mail = mail.getText().toString().trim();
                _pass = pass.getText().toString().trim();
                if (TextUtils.isEmpty(id.getText().toString().trim()) &&
                        TextUtils.isEmpty(name.getText().toString().trim()) && TextUtils.isEmpty(phn.getText().toString().trim()) && TextUtils.isEmpty(mail.getText().toString().trim()) && TextUtils.isEmpty(pass.getText().toString().trim())) {
                    id.setError("Required");
                    name.setError("Required");
                    phn.setError("Required");
                    mail.setError("Required");
                    pass.setError("Required");
                } else if (TextUtils.isEmpty(id.getText().toString().trim())) {
                    id.setError("Enter ID");
                } else if (TextUtils.isEmpty(name.getText().toString().trim())) {
                    name.setError("Required");
                } else if (!emailValidator(mail.getText().toString())) {
                    mail.setError("Please Enter Valid Email Address");
                } else if (TextUtils.isEmpty(pass.getText().toString().trim())) {
                    pass.setError("Required");
                }else if (TextUtils.isEmpty(phn.getText().toString().trim())) {
                    phn.setError("Required");
                } else if (!phValidator(phn.getText().toString())) {
                    phn.setError("Please Enter a Valid Mobile Number");
                } else {
                    mAuth.createUserWithEmailAndPassword(_mail, _pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                try{userdata();
                                    sendmessage();
                                }
                                catch (Exception e){
                                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                                }
                                Toast.makeText(getApplicationContext(), "Registered successfully!!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), StudentLogActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "Enter the valid details???", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
            public void userdata(){
                String _id,_name,_phn,_mail,_pass;
                _id=id.getText().toString().trim();
                _name=name.getText().toString().trim();
                _phn=phn.getText().toString().trim();
                _mail=mail.getText().toString().trim();
                _pass=pass.getText().toString().trim();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("users").child("Student").child(mAuth.getUid());
                Student reg = new Student(_id,_name,_phn,_mail,_pass);
                register.setValue(reg);
            }
            public boolean emailValidator(String email)
            {
                return(!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
            }
            private boolean phValidator(String phone){
                Pattern pattern;
                Matcher matcher;
                final String PHONE_PATTERN="^[0-9]{3}[0-9]{7}$";
                pattern=Pattern.compile(PHONE_PATTERN);
                matcher=pattern.matcher(phone);
                return matcher.matches();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j=new Intent(getApplicationContext(),StudentLogActivity.class);
                startActivity(j);
            }
        });
    }
    public void sendmessage(){
        int permissioncheck= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS);
        int permissioncheck1= ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.SEND_SMS);
        if (permissioncheck== PackageManager.PERMISSION_GRANTED && permissioncheck1==PackageManager.PERMISSION_GRANTED){
            Mymessage();
        }
        else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},0);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
            Mymessage();
        }
    }
    private void Mymessage(){
        String phnno=phn.getText().toString().trim();
        String msg="Registered successfully. Welcome to Student-Mark Handler!!!";
        if(!phn.getText().toString().equals("")) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phnno, null, msg, null, null);
        }else {
            Toast.makeText(getApplicationContext(),"Enter valid phone number",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case  0:
                if(grantResults.length >= 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Mymessage();
                }else {
                    Toast.makeText(getApplicationContext(),"You don't have permission",Toast.LENGTH_LONG).show();
                }

        }
    }
}