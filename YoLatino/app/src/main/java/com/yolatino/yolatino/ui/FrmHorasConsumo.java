package com.yolatino.yolatino.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yolatino.yolatino.AdaptadorHorasConsumo;
import com.yolatino.yolatino.DatosConsumoHoras;
import com.yolatino.yolatino.R;

import java.util.ArrayList;

public class FrmHorasConsumo extends Fragment {

    View vista;

    RecyclerView recyclerDatos;

    AdaptadorHorasConsumo adaptadorListaConsumo;

    private static FrmHorasConsumo instance = null;

    public FrmHorasConsumo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        instance = this;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista = inflater.inflate(R.layout.fragment_frm_horas_consumo, container, false);

        recyclerDatos = vista.findViewById(R.id.rcvListaConsumos);
        recyclerDatos.setLayoutManager(new LinearLayoutManager(getContext()));

        return vista;
    }

    public static FrmHorasConsumo getInstance() {
        return instance;
    }

    public void setearListaConsumos(ArrayList<DatosConsumoHoras> listaConsumos){

        adaptadorListaConsumo = new AdaptadorHorasConsumo(listaConsumos);

        recyclerDatos.setAdapter(adaptadorListaConsumo);

    }
}