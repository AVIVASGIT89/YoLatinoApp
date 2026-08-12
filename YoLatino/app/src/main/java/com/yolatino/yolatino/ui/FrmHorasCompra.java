package com.yolatino.yolatino.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yolatino.yolatino.AdaptadorHorasCompra;
import com.yolatino.yolatino.DatosCompraHoras;
import com.yolatino.yolatino.R;
import com.yolatino.yolatino.ui.gallery.GalleryFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FrmHorasCompra extends Fragment {

    View vista;

    RecyclerView recyclerDatos;

    AdaptadorHorasCompra adqaptadorListaCompras;

    ArrayList<DatosCompraHoras> listaArrayCompraHoras;

    String codigoCargado = "";

    public FrmHorasCompra() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_frm_horas_compra, container, false);

        recyclerDatos = vista.findViewById(R.id.rcvListaCompras);
        recyclerDatos.setLayoutManager(new LinearLayoutManager(getContext()));

        listaArrayCompraHoras = new ArrayList<>();

        listarHorasCompra();

        return vista;

    }

    private void listarHorasCompra(){

        codigoCargado = getCodigoSharedPreferences();

        if(!codigoCargado.equals("0")){

            setearCompraHoras();

        }

    }


    private void setearCompraHoras(){

        DatosCompraHoras datosCompraHoras;

        listaArrayCompraHoras.clear();

        try {

            GalleryFragment objGallery = new GalleryFragment();

            JSONArray listaCompras = objGallery.getListaCompraHoras();

            //Log.d("Response: ", listaCompras.toString());

            if(listaCompras.length() > 0){

                for(int i = 0; i < listaCompras.length(); i++){

                    JSONObject datoJson =new JSONObject(listaCompras.get(i).toString());

                    String fechaCompra = datoJson.getString("FECHA_COMPRA");
                    String horasCompra = datoJson.getString("HORAS_COMPRADAS");

                    datosCompraHoras = new DatosCompraHoras(fechaCompra, horasCompra);

                    listaArrayCompraHoras.add(datosCompraHoras);

                }

                adqaptadorListaCompras = new AdaptadorHorasCompra(listaArrayCompraHoras);

                recyclerDatos.setAdapter(adqaptadorListaCompras);

            }else {

                adqaptadorListaCompras = new AdaptadorHorasCompra(listaArrayCompraHoras);

                recyclerDatos.setAdapter(adqaptadorListaCompras);

                Toast.makeText(getContext(), "Aun no haz adquirido horas", Toast.LENGTH_SHORT).show();

            }

        }catch (JSONException e){

            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }


    private String getCodigoSharedPreferences(){

        SharedPreferences preferencias = getActivity().getSharedPreferences("codigoalumno", Context.MODE_PRIVATE);

        String getCodigo = preferencias.getString("codigo", "0");

        return  getCodigo;
    }


    @Override
    public void onResume() {
        super.onResume();

        if(codigoCargado != getCodigoSharedPreferences()){

            setearCompraHoras();

            codigoCargado = getCodigoSharedPreferences();

        }

    }
}