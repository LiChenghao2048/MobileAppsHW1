package com.example.mobileapphw1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

public class ThirdActivity extends AppCompatActivity {

    //private TextView textView_output;
    private String output = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        TextView textView_output = (TextView)findViewById(R.id.textView_output);
        //textView_output.setText(intent.getStringExtra("input"));

        try {
            JSONArray value = new JSONArray(intent.getStringExtra("value"));
            JSONArray input = new JSONArray(intent.getStringExtra("input"));
            for (int i = 0; i < input.length(); i++) {
                output += value.getString(i);
                output += input.getString(i);
            }
            output += value.getString(input.length());
            textView_output.setText(output);
            textView_output.setTextSize(30);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button button_goHome = (Button)findViewById(R.id.button_goHome);
        button_goHome.setOnClickListener(v -> {
            launchMainActivity(v);
        });
    }

    public void launchMainActivity(View view){
        Intent newIntent = new Intent(ThirdActivity.this, MainActivity.class);
        startActivity(newIntent);
    }
}
