package com.example.mobileapphw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static com.loopj.android.http.AsyncHttpClient.log;

public class MainActivity extends AppCompatActivity {

    //private Button button_next;

    private static final String api_url = "http://madlibz.herokuapp.com/api/random";
    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_next = (Button)findViewById(R.id.button_getStarted);
        button_next.setOnClickListener(v -> {
            launchSecondActivity(v);
        });
    }


    public void launchSecondActivity(View view) {

        client.addHeader("Accept", "application/json");
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //log.d("api response", new String(responseBody));

                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("title", json.getString("title"));
                    intent.putExtra("blanks", json.getString("blanks"));
                    intent.putExtra("value", json.getString("value"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                /**
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                */
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                log.d("api response", new String(responseBody));
            }
        });

    }
}