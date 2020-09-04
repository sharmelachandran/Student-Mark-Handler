package com.example.demo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class ECSFragment extends Fragment {
   private TextView ass1,ass2,ass3,avg,fedback;
   private EditText regno;
   private Button btn;
    String id;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    public ECSFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ec, container, false);
        regno=(EditText)v.findViewById(R.id.editText25);
        btn=(Button)v.findViewById(R.id.button13);
        ass1=(TextView)v.findViewById(R.id.textView11);
        ass2=(TextView)v.findViewById(R.id.textView13);
        ass3=(TextView)v.findViewById(R.id.textView14);
        avg=(TextView)v.findViewById(R.id.textView15);
        fedback=(TextView)v.findViewById(R.id.textView16);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=regno.getText().toString().trim();
                try {
                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference().child("Courses").child(id).child("ECS");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String as1 = dataSnapshot.child("ass1").getValue().toString();
                            String as2 = dataSnapshot.child("ass2").getValue().toString();
                            String as3 = dataSnapshot.child("ass3").getValue().toString();
                            String av = dataSnapshot.child("avg").getValue().toString();
                            String fed = dataSnapshot.child("fedback").getValue().toString();
                            ass1.setText("Assesment 1: " + as1);
                            ass2.setText("Assesment 2: " + as2);
                            ass3.setText("Assesment 3: " + as3);
                            avg.setText("Average : " + av);
                            fedback.setText("Feedback : " + fed);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                catch (Exception e){
                    Toast.makeText(getContext(),"your mark is not yet uploaded",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  v;
    }
}