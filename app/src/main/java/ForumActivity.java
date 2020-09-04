package com.example.demo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class ForumActivity extends AppCompatActivity {
    EditText name,query;
    Button btn,btn1;
    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        name =(EditText)findViewById(R.id.editText22);
        query =(EditText)findViewById(R.id.editText23);
        btn=(Button)findViewById(R.id.button11);
        listView = (ListView) findViewById(R.id.listview1);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Messages");
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter); 
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for(DataSnapshot ds1: ds.getChildren()){
                        String value=ds1.getValue().toString();
                        arrayList.add(value);
                        arrayAdapter.notifyDataSetChanged();}
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from=name.getText().toString().trim();
                String feedback = query.getText().toString().trim();
                Calendar calendar=Calendar.getInstance();
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("hh:mm a");
                String datetime=simpleDateFormat.format(calendar.getTime());
                String in=from+" : "+feedback+"--"+datetime+".";
                com.example.demo.msges no = new com.example.demo.msges(in);
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference register = firebaseDatabase.getReference().child("Messages");
                register.push().child("msg").setValue(no);
                Toast.makeText(getApplicationContext(), "Msg send", Toast.LENGTH_SHORT).show();
                name.getText().clear();
                query.getText().clear();
            }
        });
    }
}
 