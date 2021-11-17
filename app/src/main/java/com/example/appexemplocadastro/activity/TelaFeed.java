package com.example.appexemplocadastro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appexemplocadastro.Adapter.AdapterPacientes;
import com.example.appexemplocadastro.R;
import com.example.appexemplocadastro.model.Paciente;
import com.example.appexemplocadastro.repository.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TelaFeed extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    private RecyclerView recyclerPacientes;
    private List<Paciente> pacientes = new ArrayList<>();
    private AdapterPacientes adapterPacientes;
    private DatabaseReference pacienteUsuarioRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_feed);

        //Configuração inicial
        pacienteUsuarioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_pacientes")
                .child(ConfiguracaoFirebase.getIdUsuario());

        inicializarComponentes();

        // Configurar RecyclerView
        recyclerPacientes.setLayoutManager(new LinearLayoutManager(this));
        recyclerPacientes.setHasFixedSize(true);
        adapterPacientes = new AdapterPacientes(pacientes, this);
        recyclerPacientes.setAdapter(adapterPacientes);

        //Recuperar Pacientes para o usuario
        recuperarPacientes();

    }

    public void recuperarPacientes(){
        pacienteUsuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                pacientes.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    pacientes.add(ds.getValue(Paciente.class));
                }

                Collections.reverse(pacientes);
                adapterPacientes.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void inicializarComponentes(){
        recyclerPacientes = findViewById(R.id.recyclerPaciente);
    }

    public void clickCadastrarUsuario(View view){
        startActivity(new Intent(getApplicationContext(), CadastrarPacienteActivity.class));
    }

    //-----------------------------------------------------------------------------------------
    // Criando menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_pacientes:
                startActivity(new Intent(getApplicationContext(), CadastrarPacienteActivity.class));
                break;

            case R.id.menu_sair:
                autenticacao.signOut();
                break;
        }

        return super.onOptionsItemSelected(item);
    }





}