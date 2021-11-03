package com.example.appexemplocadastro.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appexemplocadastro.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaFeed extends AppCompatActivity {

    private Button botaoSair;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);

        botaoSair = findViewById(R.id.buttonSair);

    }

    public void clickBotaoSair(View view){
        //autenticacao.signOut();
        finish();
    }


}