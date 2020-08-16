package com.example.fasttap;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        Intent i = getIntent();
        String text = i.getStringExtra(MainActivity.EXTRA_TEXT);
        int number = i.getIntExtra(MainActivity.EXTRA_NUMBER, 0);
        TextView textView1 = (TextView) findViewById(R.id.textview1);
        TextView textView2 = (TextView) findViewById(R.id.textview2);
        textView1.setText(text);
        textView2.setText("" + number);
    }
}