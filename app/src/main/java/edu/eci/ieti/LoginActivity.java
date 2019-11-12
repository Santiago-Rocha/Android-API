package edu.eci.ieti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editUserText = (EditText) findViewById(R.id.userText);
        String user = editUserText.getText().toString();
        EditText editPasswordText = (EditText) findViewById(R.id.userPassword);
        String passwd = editPasswordText.getText().toString().trim();
        if(user.equalsIgnoreCase("") ){
            editUserText.setError("Debes escribir el nombre de usuario");
        }
        if(passwd.equalsIgnoreCase("") ){
            editPasswordText.setError("Debes escribir la contraseña");
        }
        if (user.length() > 0 && passwd.length() > 0){
            startActivity(intent);
        }


    }
}
