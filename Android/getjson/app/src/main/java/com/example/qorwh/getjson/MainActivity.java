package com.example.qorwh.getjson;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity {

    private EditText editText;
    private EditText T1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.searchButton);
        editText = (EditText)findViewById(R.id.searchText);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //PhPtest(editText.getText().toString());

                Intent i = new Intent(MainActivity.this, SearchResultActivity.class);
                i.putExtra("searchText",editText.getText().toString());
                startActivity(i);

            }
        });
    }


}

