package com.andreshdez.loginconhilos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button btnIniciarSesion;
    EditText txtUsuario, txtPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIniciarSesion = (Button) findViewById(R.id.btnLogin);
        txtUsuario = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.loading);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task().execute(txtUsuario.getText().toString());
            }
        });
    }
    class Task extends AsyncTask<String, Void, String>{
        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
            btnIniciarSesion.setEnabled(false);
        }
        @Override
        protected String doInBackground(String... strings){
            try{
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return strings[0];
        }
        @Override
        protected void onPostExecute(String s){
            progressBar.setVisibility(View.VISIBLE);
            btnIniciarSesion.setEnabled(true);
            Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
            intent.putExtra("usuario",txtUsuario.getText().toString());
            startActivity(intent);
        }
    }
}
