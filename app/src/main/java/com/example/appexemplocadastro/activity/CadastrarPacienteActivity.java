package com.example.appexemplocadastro.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appexemplocadastro.R;
import com.example.appexemplocadastro.helper.Permissoes;

public class CadastrarPacienteActivity extends AppCompatActivity {

    private EditText campoNomePaciente, campoEmailPaciente, campoDataNascimento, campoTelefone, campoConsulta;
    private Spinner spinnerEstado, spinnerCritico;
    private Button botaoCadastrarPaciente;
    private ImageView imagePaciente;

    private String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pacientes);

        // Validar permissões
        Permissoes.validarPermissoes(permissoes, this, 1);

        inicializarConponentes();

    }

    private void inicializarConponentes(){

        campoNomePaciente = findViewById(R.id.editNomePaciente);
        campoEmailPaciente = findViewById(R.id.editEmailPaciente);
        campoDataNascimento = findViewById(R.id.editDataNascimento);
        campoTelefone = findViewById(R.id.editTelefone);
        campoConsulta = findViewById(R.id.editConsulta);

    }

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