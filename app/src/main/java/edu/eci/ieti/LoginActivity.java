package edu.eci.ieti;

import androidx.appcompat.app.AppCompatActivity;
import edu.eci.ieti.pojos.LoginWrapper;
import edu.eci.ieti.pojos.Token;
import edu.eci.ieti.services.IAuthService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private IAuthService authService;
    private final ExecutorService executorService = Executors.newFixedThreadPool( 1 );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") //localhost for emulator
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(IAuthService.class);
    }

    public void sendLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editUserText = (EditText) findViewById(R.id.userText);
        final String user = editUserText.getText().toString();
        EditText editPasswordText = (EditText) findViewById(R.id.userPassword);
        final String passwd = editPasswordText.getText().toString().trim();
        if(user.equalsIgnoreCase("") ){
            editUserText.setError("Debes escribir el nombre de usuario");
        }
        if(passwd.equalsIgnoreCase("") ){
            editPasswordText.setError("Debes escribir la contraseña");
        }
        if (user.length() > 0 && passwd.length() > 0){
            executorService.execute( new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        Response<Token> response =
                                authService.login( new LoginWrapper( user, passwd ) ).execute();
                        Token token = response.body();
                        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.remove("TOKEN");
                        editor.putString("TOKEN", "Bearer "+token.getAccessToken());
                        editor.apply();
                    }
                    catch ( IOException e )
                    {
                        e.printStackTrace();
                    }
                }
            } );
            //startActivity(intent);
        }


    }
}
