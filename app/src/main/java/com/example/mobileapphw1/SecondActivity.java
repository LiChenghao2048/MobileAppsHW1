package com.example.mobileapphw1;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.loopj.android.http.AsyncHttpClient.log;

public class SecondActivity extends AppCompatActivity {

    private TextView textView_title;
    private String blanks;
    private String value;

    private LinearLayout blanks_linear_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView_title = findViewById(R.id.textView_title);
        Intent intent = getIntent();
        textView_title.setText(intent.getStringExtra("title"));

        blanks_linear_layout = findViewById(R.id.blanks_linear_layout);

        blanks = intent.getStringExtra("blanks");
        value = intent.getStringExtra("value");

        try {
            JSONArray jsonArray = new JSONArray(blanks);
            //log.d("jsonArray", String.valueOf(jsonArray));
            for (int i = 0; i < jsonArray.length(); i++) {
                TextView textView = new TextView(this);
                textView.setText(jsonArray.get(i).toString());
                textView.setTextSize(20);
                textView.setTypeface(null, Typeface.ITALIC);
                blanks_linear_layout.addView(textView);
                EditText editText = new EditText(this);
                editText.setTag("input");
                blanks_linear_layout.addView(editText);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Button button_generate = (Button)findViewById(R.id.button_generate);
        button_generate.setOnClickListener(v -> {
            launchThirdActivity(v);
        });

    }

    public void launchThirdActivity(View view) {
        Intent newIntent = new Intent(SecondActivity.this, ThirdActivity.class);
        newIntent.putExtra("value", value);
        ArrayList<String> inputs = new ArrayList<String>();
        for (int i = 1; i < blanks_linear_layout.getChildCount(); i=i+2) {
            EditText curr = (EditText)blanks_linear_layout.getChildAt(i);
            String input = curr.getText().toString();
            if (input.equals("")) {
                Toast toast = Toast.makeText(this, R.string.missing_field, Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            inputs.add(input);
        }
        newIntent.putExtra("input", inputs.toString());
        startActivity(newIntent);
    }
}
