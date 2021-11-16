package com.example.appexemplocadastro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appexemplocadastro.R;
import com.google.firebase.auth.FirebaseAuth;

public class TelaFeed extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_pacientes:
                startActivity(new Intent(getApplicationContext(), MeusPacientesActivity.class));
                break;

            case R.id.menu_sair:
                autenticacao.signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}