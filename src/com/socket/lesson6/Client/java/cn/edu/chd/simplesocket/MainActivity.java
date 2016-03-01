package cn.edu.chd.simplesocket;

import android.os.AsyncTask;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    TextView text;
    EditText inputText;
    EditText ip;

    public static final int PORT = 14000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ip = (EditText) findViewById(R.id.inputIP);
        ip.setText("10.0.2.2");
        inputText = (EditText) findViewById(R.id.inputLine);
        text = (TextView) findViewById(R.id.textView);
        //connect to the server
        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String connIP = ip.getText().toString();
                connect(connIP);
            }
        });
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
    }


    Socket socket = null;

    DataInputStream dataIn = null;
    DataOutputStream dataOut = null;

    public void connect(String connIP) {


        AsyncTask<String, String, Void> read = new AsyncTask<String, String, Void>() {

            @Override
            protected Void doInBackground(String... arg0) {
                try {
                    socket = new Socket(arg0[0], PORT);
                    //Looper.prepare();
                    //Toast.makeText(MainActivity.this, arg0[0], Toast.LENGTH_SHORT).show();
                    //Looper.loop()
                    dataIn = new DataInputStream(socket.getInputStream());
                    dataOut = new DataOutputStream(socket.getOutputStream());
                    publishProgress("@success");
                } catch (UnknownHostException e1) {
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "未知主机，无法建立链接", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e1.printStackTrace();
                } catch (IOException e1) {
                    Looper.prepare();
                    Toast.makeText(MainActivity.this, "无法建立链接", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                    e1.printStackTrace();
                }
                try {
                    String line;
                    while ((line = dataIn.readUTF()) != null){
                        publishProgress(line);
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("@success")) {
                    Toast.makeText(MainActivity.this, "链接成功！", Toast.LENGTH_SHORT).show();
                }
//                Button button = (Button) findViewById(R.id.connect);
//                button.setText("连接成功");

                text.append("[Server]："+values[0]+"\n");
                super.onProgressUpdate(values);
            }
        };
        read.execute(connIP);

    }


    public void send() {
        try {
            text.append("[Client]：" + inputText.getText().toString() + "\n");
            dataOut.writeUTF(inputText.getText().toString()+"\n");
            inputText.setText("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
