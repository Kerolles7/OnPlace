package com.example.fady.onplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText email, password;
    private Button btnlogin;
    private TextView txregister;

    private DbHelper db;
    private LogStatus logStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        db = new DbHelper(this);
        logStatus = new LogStatus(this);

        email = (EditText) findViewById(R.id.edmail);
        password = (EditText) findViewById(R.id.edpass);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        txregister = (TextView) findViewById(R.id.txregister);

        btnlogin.setOnClickListener(this);
        txregister.setOnClickListener(this);

        if (logStatus.loggedin())
        {
            startActivity(new Intent(Login_Activity.this, Map_Activity.class));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnlogin:

                login();
                break;
            case R.id.txregister:
                startActivity(new Intent(Login_Activity.this, Register_Activity.class));
                email.setText("");
                password.setText("");
                break;

            default:
        }

    }


    public void login()
    {
        String ema = email.getText().toString();
        String pas = password.getText().toString();

        if (db.getUser(ema, pas))
        {
            logStatus.setLoggedin(true);
            startActivity(new Intent(Login_Activity.this, Map_Activity.class));
            finish();
        }
        else if (ema.isEmpty() && pas.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please enter email & password" , Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong email & Password" , Toast.LENGTH_LONG).show();
        }
    }
}
