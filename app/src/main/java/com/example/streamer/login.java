package com.example.streamer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.streamer.VideoStreaming.homepage;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import com.google.android.material.textfield.TextInputEditText;


public class login extends AppCompatActivity {

    TextInputEditText studentid,password;
    Button Login;

    ProgressBar progressBar;
    String result="";
    String id="";
    public static final String UserId = "logintomain";
    String fname, uname,pswd,eml;
    SessionManager sessionManager;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            studentid=findViewById(R.id.username);
            password=findViewById(R.id.password);
            Login=findViewById(R.id.buttonLogin);

            progressBar=findViewById(R.id.progress);

            //Initialize SessionManager
            sessionManager = new SessionManager((getApplicationContext()));


            Login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    uname=String.valueOf(studentid.getText());
                    pswd=String.valueOf(password.getText());


                    if(!uname.equals("") && !pswd.equals(""))
                    {
                        progressBar.setVisibility(View.VISIBLE);
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //Starting Write and Read data with URL
                                //Creating array for parameters
                                String[] field = new String[2];

                                field[0] = "studentid";
                                field[1] = "password";

                                //Creating array for data
                                String[] data = new String[2];

                                data[0] = uname;
                                data[1] = pswd;
                                String r="All fields are required";
                                String str="Login Success";
                                PutData putData = new PutData("https://o2academia.org/demo_crud/login.php", "POST", field, data);
                                if (putData.startPut()) {
                                    if (putData.onComplete()) {
                                        progressBar.setVisibility(View.GONE);
                                        result = putData.getResult().toString().toUpperCase().trim();
                                        //Toast.makeText(Login.this,result,Toast.LENGTH_LONG).show();
                                        int a=result.length();
                                        int b=str.length();

                                        if(result.contains("SUCCESS"))
                                        {
                                            //Set login true
                                            sessionManager.setLogin(true);
                                            //store login in session
                                            sessionManager.setLogin(true);
                                            //store username in session
                                            sessionManager.setUsername(uname);

                                            id=studentid.getText().toString().trim();

                                            Toast.makeText(getApplicationContext(),"result",Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(getApplicationContext(), homepage.class);
                                            intent.putExtra(UserId,id);
                                            startActivity(intent);
                                            //           finish();
                                        }

                                        else{
                                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                        }
                                        //End ProgressBar (Set visibility to GONE)

                                    }
                                }
                                //End Write and Read data with URL
                            }
                        });
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
                    }

                }
            });


            //If user already logged in
            if (sessionManager.getLogin())
            {
                //Redirect activity
                Intent intent=new Intent(getApplicationContext(), homepage.class);
                String o = sessionManager.getUsername(uname);
                intent.putExtra(UserId,o);
                startActivity(intent);

            }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.loginhelpmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.loginHelpBtn:

                Toast.makeText(login.this, " Please Enter Student Id as Username and Password to Login ", Toast.LENGTH_LONG).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}