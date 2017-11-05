package com.example.fady.onplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText email, password;
    private Button btnregsiter;
    private TextView txlogin;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        db = new DbHelper(this);

        email =(EditText) findViewById(R.id.edmail);
        password = (EditText) findViewById(R.id.edpass);
        btnregsiter =(Button) findViewById(R.id.btnregister);
        txlogin = (TextView) findViewById(R.id.txlogin);

        btnregsiter.setOnClickListener(this);
        txlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnregister:
                register();
                break;

            case R.id.txlogin:
                startActivity(new Intent(Register_Activity.this, Login_Activity.class));
                break;
            default:
        }

    }

    public void register()
    {
        String ema = email.getText().toString();
        String pass = password.getText().toString();

        if (ema.isEmpty() && pass.isEmpty())
        {
            displayToast("User name and password field empty");
        }
        else
        {
            db.addUser(ema, pass);
            displayToast("User registred");
            finish();

        }
    }

    public void displayToast (String message)
    {
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_LONG).show();
    }
}
