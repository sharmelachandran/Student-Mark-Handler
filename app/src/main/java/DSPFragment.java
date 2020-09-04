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


/**
 * A simple {@link Fragment} subclass.
 */
public class DSPFragment extends Fragment {
    private EditText regno;
    private TextView ass1,ass2,ass3,average,fed;
    private Button btn;
    private FirebaseDatabase db;
    private DatabaseReference ref;
    String id;

    public DSPFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_ds, container, false);
        regno=(EditText)v.findViewById(R.id.editText25);
        ass1=(TextView)v.findViewById(R.id.textView17);
        ass2=(TextView)v.findViewById(R.id.textView13);
        ass3=(TextView)v.findViewById(R.id.textView14);
        average=(TextView)v.findViewById(R.id.textView15);
        fed=(TextView)v.findViewById(R.id.textView16);
        btn=(Button)v.findViewById(R.id.button13);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=regno.getText().toString().trim();
                try{
                    db=FirebaseDatabase.getInstance();
                    ref=db.getReference().child("Courses").child(id).child("DSP");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String as1=dataSnapshot.child("ass1").getValue().toString();
                            String as2=dataSnapshot.child("ass2").getValue().toString();
                            String as3=dataSnapshot.child("ass3").getValue().toString();
                            String av=dataSnapshot.child("avg").getValue().toString();
                            String fedback=dataSnapshot.child("fedback").getValue().toString();
                            ass1.setText("Assesment 1 :"+as1);
                            ass2.setText("Assesment 1 :"+as2);
                            ass3.setText("Assesment 1 :"+as3);
                            average.setText("Assesment 1 :"+av);
                            fed.setText("Assesment 1 :"+fedback);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }catch (Exception e){
                    Toast.makeText(getContext(),"your mark is not yet uploaded",Toast.LENGTH_SHORT).show();

                }
            }
        });

return  v;
    }

}
