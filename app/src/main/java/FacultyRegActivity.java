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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FacultyRegActivity extends AppCompatActivity {
    public static final String userDetails = "com.example.demo";
    Button btn;
    TextView tv;
    EditText name,id,age,phn,mail,pass;
    public FacultyRegActivity() {
    }

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
        setContentView(R.layout.activity_faculty_reg);
        mAuth = FirebaseAuth.getInstance();
        id=(EditText)findViewById(R.id.editText8);
        name=(EditText)findViewById(R.id.editText3);
        age=(EditText)findViewById(R.id.editText4);
        phn=(EditText)findViewById(R.id.editText5);
        mail=(EditText)findViewById(R.id.editText6);
        pass=(EditText)findViewById(R.id.editText7);
        btn=(Button)findViewById(R.id.button4);
        tv=(TextView)findViewById(R.id.textView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String _name, _phn, _mail, _pass, _id, _age;
                _id = id.getText().toString().trim();
                _name = name.getText().toString().trim();
                _age = age.getText().toString().trim();
                _phn = phn.getText().toString().trim();
                _mail = mail.getText().toString().trim();
                _pass = pass.getText().toString().trim();
                if (TextUtils.isEmpty(id.getText().toString().trim()) &&
                        TextUtils.isEmpty(name.getText().toString().trim()) && TextUtils.isEmpty(age.getText().toString().trim())
                        && TextUtils.isEmpty(phn.getText().toString().trim()) && TextUtils.isEmpty(mail.getText().toString().trim()) && TextUtils.isEmpty(pass.getText().toString().trim())) {
                    id.setError("Required");
                    name.setError("Required");
                    age.setError("Required");
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
                } else if (TextUtils.isEmpty(age.getText().toString().trim())) {
                    age.setError("Enter Username");
                } else if (!ageValidator(age.getText().toString())) {
                    age.setError("Age must be Between 0 to 100");
                } else if (TextUtils.isEmpty(phn.getText().toString().trim())) {
                    phn.setError("Required");
                } else if (!phValidator(phn.getText().toString())){
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
                                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                                startActivity(i);

                            } else {
                                Toast.makeText(getApplicationContext(), "Enter the valid details???", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
            public void userdata(){
                String _id = id.getText().toString().trim();
                String _name = name.getText().toString().trim();
                String _age = age.getText().toString().trim();
                String _phn = phn.getText().toString().trim();
                String _mail = mail.getText().toString().trim();
                String _pass = pass.getText().toString().trim();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("users").child("Faculty").child(mAuth.getUid());
                Faculty faculty = new Faculty(_id,_name, _age,_phn, _mail, _pass);
                register.setValue(faculty);
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
            private boolean ageValidator(String age){
                Pattern pattern;
                Matcher matcher;
                final String PHONE_PATTERN="^[0-9]{2}$";
                pattern=Pattern.compile(PHONE_PATTERN);
                matcher=pattern.matcher(age);
                return matcher.matches();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(getApplicationContext(), SecondActivity.class);
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
