package com.example.appexemplocadastro.model;

import com.example.appexemplocadastro.repository.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;

public class Paciente {

    private String idPaciente;
    private String estado;
    private String Sanguineo;
    private String nome;
    private String email;
    private String dataNascimento;
    private String telefone;
    private String consulta;
    private String foto;

    public Paciente() {
        DatabaseReference pacienteRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_pacientes");
        setIdPaciente(pacienteRef.push().getKey());
    }

    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference pacienteRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_pacientes");

        pacienteRef.child(idUsuario)
                .child(getIdPaciente())
                .setValue(this);

    }

    public void remover(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference pacienteRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_pacientes")
                .child(idUsuario)
                .child(getIdPaciente());

        pacienteRef.removeValue();
    }

    public String getIdPaciente() { return idPaciente;  }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSanguineo() {
        return Sanguineo;
    }

    public void setSanguineo(String sanguineo) {
        Sanguineo = sanguineo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
