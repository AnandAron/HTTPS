package iacademia.httptesting;

import android.os.AsyncTask;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {
    public static String query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void post(View view) {
        new PostTask().execute();
        EditText et;
        et = (EditText) findViewById(R.id.editText);
        query=et.getText().toString();
    }

    }

class PostTask extends AsyncTask<URL,Void,String> {

    @Override
    protected String doInBackground(URL... urls) {
        try {
            String response="";
            URL url=new URL("http://192.168.43.194/8888");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            OutputStream os = conn.getOutputStream();
            os.write(MainActivity.query.getBytes());
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            while ((line = br.readLine()) != null) {
                response += line;
                Log.e("Response", response);


            }

    } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }return null;
    }}