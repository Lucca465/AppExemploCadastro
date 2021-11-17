package com.example.appexemplocadastro.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appexemplocadastro.R;
import com.example.appexemplocadastro.model.Paciente;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterPacientes extends RecyclerView.Adapter<AdapterPacientes.MyViewHolder> {

    private List<Paciente> pacientes;
    private Context context;

    public AdapterPacientes(List<Paciente> pacientes, Context context) {
        this.pacientes = pacientes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paciente, parent, false);

        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Paciente paciente = pacientes.get(position);
        holder.nome.setText(paciente.getNome());
        holder.telefone.setText(paciente.getTelefone());
        holder.consulta.setText(paciente.getConsulta());

        //recuperar foto
        String urlFoto = paciente.getFoto();
        Picasso.get().load(urlFoto).into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nome;
        TextView telefone;
        TextView consulta;
        ImageView foto;

        public MyViewHolder(View itemView){
            super(itemView);

            nome = itemView.findViewById(R.id.textNomeView);
            telefone = itemView.findViewById(R.id.textTelefoneView);
            consulta = itemView.findViewById(R.id.textConsultaView);
            foto = itemView.findViewById(R.id.imagePacienteView);

        }


    }

}
