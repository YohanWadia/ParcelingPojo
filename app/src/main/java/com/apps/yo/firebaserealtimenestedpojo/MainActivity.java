package com.apps.yo.firebaserealtimenestedpojo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference dbRef;
    Student s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbRef = FirebaseDatabase.getInstance().getReference("NestedPOJO/Students");
    }

    public void AddNestedData(View v){
        String name ="oho";
        int marks = 15;

        String uniqueId = dbRef.push().getKey();

        Student s = new Student(uniqueId,name,marks);
        s.setAddress(new Address("at home","work work.."));
        s.setTelephone(new Telephone("123","999"));

        dbRef.child(uniqueId).setValue(s);

    }

    public void ReadNestedData(View v){
        s=null;
        DatabaseReference dbref2 = FirebaseDatabase.getInstance().getReference("NestedPOJO/Students");

        dbref2.child("-LhjUbt4zl2_RKhYRW1h").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Log.e("SNAPSHOT", dataSnapshot.toString());
                s = dataSnapshot.getValue(Student.class);
                if(s.getTelephone()==null){
                    Log.e("SNAPSHOT", "it is null");//-LhjTPz5M6xuxP3_ROc5 ....gives null
                }
                else {
                    Log.e("SNAPSHOT",s.getName() +"|"+ s.getTelephone().getWork()); //-LhjUbt4zl2_RKhYRW1h ...has Telephone obj
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //while checking Parcable only use the one who has the telephone Object.

    public void Send2NextActivity(View v){

        Intent i = new Intent(this, Main2Activity.class);
        i.putExtra("myObject",s);
        startActivity(i);
    }

}

//==================================================================
//the reason the below code fails is because
// one entry has everything of the nested POJO, while another doesnt have TelePhone & Address data entered
// into the Firebase DB. So the mapping produced a null pointer when you call s1.getTelephone().getWork()
// on the object that doesnot have that data.
// So to make such POJOs work, I suggest to pass some initialised Values as place holders, so on Reading
// them you dont get a null pointer error



/*
DatabaseReference dbref2 = FirebaseDatabase.getInstance().getReference("NestedPOJO/Students");
dbref2.addListenerForSingleValueEvent(new ValueEventListener() {
public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.e("SNAPSHOT", dataSnapshot.toString());

        for(DataSnapshot ds: dataSnapshot.getChildren()){
        Student s1 = ds.getValue(Student.class);
        Log.e("SNAPSHOT", s1.getName() +"|" + s1.getTelephone().getWork());
        }
}
*/
