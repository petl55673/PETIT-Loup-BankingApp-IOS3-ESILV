package com.example.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class Activity2 extends AppCompatActivity {

    public static TextView textViewAmount;
    private static final String FILE_NAME = "accounts.txt";
    Button button2;
    Button button3;
    Button refreshbutton;
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    private static final String API_KEY = BuildConfig.ApiKey;


    public void writeFile(String data) throws IOException {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILE_NAME, MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
            Toast toast = Toast.makeText(Activity2.this, "your accounts have been saved", Toast.LENGTH_LONG);
            toast.show();
        }
        catch (IOException e) {
            Log.e("Exception", "an error has occured: " + e.toString());
            Toast toast = Toast.makeText(Activity2.this, "an error has occured", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void importData() {
        try {
            URL url = new URL(API_KEY);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONArray JA = new JSONArray(data);
            for (int i = 0; i < JA.length(); i++) {
                JSONObject JO = (JSONObject) JA.get(i);
                singleParsed = "Id:" + JO.get("id") + "\n" + "Account_Name:" + JO.get("accountName") + "\n" + "Amount:" + JO.get("amount") + "\n" + "iban:" + JO.get("iban") + "\n" + "currency:" + JO.get("currency") + "\n";

                dataParsed = dataParsed + singleParsed;
            }
        } catch (Exception e) {
            Log.e("Exchange-JSON", "Cannot found http server", e);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_activity2);
        GetData process = new GetData();
        process.execute();

        textViewAmount = findViewById(R.id.textView2);
        textViewAmount.setMovementMethod(new ScrollingMovementMethod());

        button2 = (Button) findViewById(R.id.buttonsave);
        button3 = (Button) findViewById(R.id.buttonload);
        refreshbutton = (Button) findViewById(R.id.buttonrefresh);

        textViewAmount.setText(dataParsed);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textViewAmount.getText().toString();
                try {
                    writeFile(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null){
                        sb.append(text).append("\n");

                    }
                    textViewAmount.setText(sb.toString());
                    Toast toast = Toast.makeText(Activity2.this, "File Loaded", Toast.LENGTH_LONG);
                    toast.show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    if ( fis != null){
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }



    protected void onResume(){
        super.onResume();

    }
}