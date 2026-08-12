package com.yolatino.yolatino;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorHorasCompra extends RecyclerView.Adapter<AdaptadorHorasCompra.ViewHolderDatos> {

    ArrayList<DatosCompraHoras> listaCompraHoras;

    public AdaptadorHorasCompra(ArrayList<DatosCompraHoras> listaCompraHoras){

        this.listaCompraHoras = listaCompraHoras;

    }

    @NonNull
    @Override
    public AdaptadorHorasCompra.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_compras, null, false);

        return new ViewHolderDatos(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {

        holder.fechaCompra.setText("Fecha: " + listaCompraHoras.get(position).getFechaCompra());
        holder.horasCompra.setText("Horas: " + listaCompraHoras.get(position).getHorasCompra());

    }

    @Override
    public int getItemCount() {
        return listaCompraHoras.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView fechaCompra, horasCompra;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            fechaCompra = (TextView) itemView.findViewById(R.id.txv_fecha_compra);
            horasCompra = (TextView) itemView.findViewById(R.id.txv_horas_compra);
        }
    }
}
