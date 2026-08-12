package com.yolatino.yolatino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorHorasConsumo extends RecyclerView.Adapter<AdaptadorHorasConsumo.ViewHolderDatos> {

    ArrayList<DatosConsumoHoras> listaConsumoHoras;

    public AdaptadorHorasConsumo(ArrayList<DatosConsumoHoras> listaConsumoHoras){

        this.listaConsumoHoras = listaConsumoHoras;

    }

    @NonNull
    @Override
    public AdaptadorHorasConsumo.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_consumo, null, false);

        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.claseBaile.setText("Clase: " + listaConsumoHoras.get(position).getClaseBaile());
        holder.fechaConsumo.setText("Fecha: " + listaConsumoHoras.get(position).getFechaConsumo());
        holder.horasConsumo.setText("Horas: " + listaConsumoHoras.get(position).getHorasConsumo());

    }

    @Override
    public int getItemCount() {
        return listaConsumoHoras.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{

        TextView fechaConsumo, claseBaile, horasConsumo;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            claseBaile = (TextView) itemView.findViewById(R.id.txv_nombre_clase);
            fechaConsumo = (TextView) itemView.findViewById(R.id.txv_fecha_clase);
            horasConsumo = (TextView) itemView.findViewById(R.id.txv_horas_clase);

        }
    }
}
