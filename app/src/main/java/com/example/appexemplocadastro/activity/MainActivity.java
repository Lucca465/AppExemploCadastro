package com.example.appexemplocadastro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appexemplocadastro.R;
import com.example.appexemplocadastro.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    //private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }*/

    public void btEntrar(View view){

        startActivity(new Intent(this, TelaLogin.class));
    }

    public void btCadastrar(View view){

        startActivity(new Intent(this, TelaCadastro.class));
    }

    /*
    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //para testar
        autenticacao.signOut();

        if(autenticacao.getCurrentUser() != null){
            abrirTelaFeed();
        }
    }

    public void abrirTelaFeed(){
        startActivity(new Intent(this, TelaFeed.class));
    }*/

}