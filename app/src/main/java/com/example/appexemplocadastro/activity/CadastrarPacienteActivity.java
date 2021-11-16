package com.example.appexemplocadastro.activity;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appexemplocadastro.R;
import com.example.appexemplocadastro.helper.Permissoes;

public class CadastrarPacienteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText campoNomePaciente, campoEmailPaciente, campoDataNascimento, campoTelefone, campoConsulta;
    private Spinner campoEstado, campoSanguineo;
    private Button botaoCadastrarPaciente;
    private ImageView imagePaciente;

    private String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private String fotoRecuperada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pacientes);

        // Validar permissões
        Permissoes.validarPermissoes(permissoes, this, 1);

        inicializarConponentes();
        carregarDadosSpinner();
    }

    public void validarDadosCadastrados(View view){
        String estado = campoEstado.getSelectedItem().toString();
        String sanguineo = campoSanguineo.getSelectedItem().toString();
        String nome = campoNomePaciente.getText().toString();
        String email = campoEmailPaciente.getText().toString();
        String dataNascimento = campoDataNascimento.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String consulta = campoConsulta.getText().toString();

        if(!estado.isEmpty()){
            if(!sanguineo.isEmpty()){
                if(!nome.isEmpty()){
                    if(!email.isEmpty()){
                        if(!dataNascimento.isEmpty()){
                            if(!telefone.isEmpty()){
                                if(!consulta.isEmpty()){
                                    salvar();
                                } else{
                                    exibirMensagemErro("Preencha o campo data / hora da consulta!");
                                }
                            } else{
                                exibirMensagemErro("Preencha o campo Telefone!");
                            }
                        } else{
                            exibirMensagemErro("Preencha o campo Data de nascimento!");
                        }
                    } else{
                        exibirMensagemErro("Preencha o campo email!");
                    }
                } else{
                    exibirMensagemErro("Preencha o campo nome!");
                }
            } else{
                exibirMensagemErro("Preencha o campo sanguineo!");
            }
        } else{
            exibirMensagemErro("Preencha o campo estado!");
        }
    }

    private void exibirMensagemErro(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

    public void salvar(){
        Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
    }

    private void inicializarConponentes(){

        campoNomePaciente = findViewById(R.id.editNomePaciente);
        campoEmailPaciente = findViewById(R.id.editEmailPaciente);
        campoDataNascimento = findViewById(R.id.editDataNascimento);
        campoTelefone = findViewById(R.id.editTelefone);
        campoConsulta = findViewById(R.id.editConsulta);
        campoEstado = findViewById(R.id.spinnerEstado);
        campoSanguineo = findViewById(R.id.spinnerSanguineo);

        imagePaciente = findViewById(R.id.imagePaciente);
        imagePaciente.setOnClickListener(this);

    }

    private void carregarDadosSpinner(){

        //CampoEstado
        String[] estados = getResources().getStringArray(R.array.estados);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, estados
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoEstado.setAdapter(adapter);

        //campoCategoria
        String[] sanguineo = getResources().getStringArray(R.array.sanguineo);
        ArrayAdapter<String> adapterSanguineo = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, sanguineo
        );
        adapterSanguineo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campoSanguineo.setAdapter(adapterSanguineo);

    }

    //------------------------------------------------------------------------------------------------
    //escolher foto da galeria
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imagePaciente:
                escolherImagem(1);
                break;
        }
    }

    public void escolherImagem(int requestCode){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            //Recuperar Imagem
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            //Configurar imagem no ImageView
            imagePaciente.setImageURI(imagemSelecionada);
            fotoRecuperada = caminhoImagem;
        }

    }
    //-----------------------------------------------------------------------------------------------
    //Permissão de acesso na galeria
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int permissaoResultado : grantResults){
            if(permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o App é necessario aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

}