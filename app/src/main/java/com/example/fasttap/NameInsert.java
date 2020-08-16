package com.example.fasttap;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NameInsert extends MainActivity {
    EditText Name;
    Button btnName;

    String name;
    private static final String TAG = "NameInsert";
    private static final String KEY_NAME = "name";


    private FirebaseFirestore db =  FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_insert);



        Name = (EditText) findViewById(R.id.Nameid);


    }


    public void saveData(View v){
        name = Name.getText().toString();
        Map<String , Object> note = new HashMap<>();

        note.put(KEY_NAME , name);
        db.collection("ScorePage").document("Score").set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(NameInsert.this , "Name saved" , Toast.LENGTH_LONG).show();

                        openGame();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NameInsert.this , "Error!" , Toast.LENGTH_LONG).show();
                        Log.d(TAG, e.toString());


                    }
                });

    }
    public void openGame() {

        name = Name.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_TEXT, name);
        startActivity(intent);

    }


}