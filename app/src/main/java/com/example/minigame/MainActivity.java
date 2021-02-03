package com.example.minigame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button b;
    private Button b1;
    private Button b2;
    private Button b3;
    private TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button3);
        b2 = (Button) findViewById(R.id.button4);
        b3 = (Button) findViewById(R.id.button);
        t = (TextView) findViewById(R.id.textView2);
        t.setTextSize(75);


        b.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button3) {
            Intent intent = new Intent(MainActivity.this, Snake.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.button) {
            Intent intent2 = new Intent(MainActivity.this, Clickme.class);
            startActivityForResult(intent2, 2);
        }
        if (v.getId() == R.id.button4) {
            Intent intent2 = new Intent(MainActivity.this, Rollinball.class);
            startActivityForResult(intent2, 2);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {



        }
    }
}