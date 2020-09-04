package com.example.demo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class FifthActivity extends AppCompatActivity {
    private Spinner spinner,spinner2;
    EditText ass1,ass2,ass3,fedb;
    Student student;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    Button btn;
    String item1,item2;
    List<String> Courses = new ArrayList<>();
    List<String> regno = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        spinner = (Spinner) findViewById(R.id.spinner2);
        spinner2 = (Spinner) findViewById(R.id.spinner3);
        ass1 = findViewById(R.id.editText24);
        ass2 = findViewById(R.id.editText26);
        ass3 = findViewById(R.id.editText27);
        fedb =findViewById(R.id.editText2);
        btn = findViewById(R.id.button10);
        regno.add(0, "Select Regno");
        final ArrayAdapter<String> regnoadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regno);
        spinner.setAdapter(regnoadapter);
        for (int i=1;i<=52;i++) {
            String tem ="17171";
            if(i<10)
                tem="171710";
            regno.add(tem+i);
        }
        regnoadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(regnoadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select Regno")) {
                    Toast.makeText(getApplicationContext(), "Select the Regno", Toast.LENGTH_SHORT).show();
                } else {
                    item2 = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Select the Regno", Toast.LENGTH_SHORT).show();
            }
        });
        Courses.add(0, "Select Course");
        Courses.add("ECS");
        Courses.add("SOP");
        Courses.add("CN");
        Courses.add("DSP");
        Courses.add("SEM");
        ArrayAdapter<String> courseadapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Courses);
        courseadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(courseadapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Select Course")) {
                    Toast.makeText(getApplicationContext(), "Select the course first", Toast.LENGTH_SHORT).show();
                } else {
                    item1 = adapterView.getItemAtPosition(i).toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Select the course first", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{Integer _m1=Integer.parseInt(ass1.getText().toString().trim());
                Integer _m2=Integer.parseInt(ass2.getText().toString().trim());
                Integer _m3=Integer.parseInt(ass3.getText().toString().trim());
                String fedback=fedb.getText().toString().trim();
                FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("Courses").child(item2).child(item1);
                Courses cour= new Courses(_m1,_m2,_m3,fedback);
                register.setValue(cour);
                Toast.makeText(getApplicationContext(),"marks updated",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),FifthActivity.class);
                startActivity(i);}
                catch (DatabaseException e){
                    Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
