package com.yolatino.yolatino.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.yolatino.yolatino.AdaptadorTabLayout;
import com.yolatino.yolatino.AmbienteEjecucion;
import com.yolatino.yolatino.DatosConsumoHoras;
import com.yolatino.yolatino.R;
import com.yolatino.yolatino.databinding.FragmentGalleryBinding;
import com.yolatino.yolatino.ui.FrmHorasConsumo;
import com.yolatino.yolatino.ui.LoadingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    View vista;

    ViewPager2 viewPager;

    TabLayout tabLayout;

    AdaptadorTabLayout adaptadorTabLayout;

    ArrayList<DatosConsumoHoras> listaArrayConsumoHoras;

    String dominioAmbiente = AmbienteEjecucion.getAmbienteEjecucion();

    LoadingBar loadingBar;

    Button btnBuscar;

    TextView txvNombreAlumno, txvHorasAlumno;

    EditText edtCodigoAlumno;

    RequestQueue requestVolley;

    public static JSONArray jsonListaCompraHoras;

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        vista =  inflater.inflate(R.layout.fragment_gallery, container, false);

        tabLayout = (TabLayout) vista.findViewById(R.id.tab_layout);
        viewPager = (ViewPager2) vista.findViewById(R.id.view_pager);

        adaptadorTabLayout = new AdaptadorTabLayout(this);

        listaArrayConsumoHoras = new ArrayList<>();

        viewPager.setAdapter(adaptadorTabLayout);

        loadingBar = new LoadingBar(getActivity());

        btnBuscar = (Button) vista.findViewById(R.id.btn_buscar);

        edtCodigoAlumno = (EditText) vista.findViewById(R.id.edt_codigo_alumno);

        txvNombreAlumno = (TextView) vista.findViewById(R.id.txv_nombre_alumno);
        txvHorasAlumno = (TextView) vista.findViewById(R.id.txv_horas_alumno);

        requestVolley = Volley.newRequestQueue(getContext());

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                tabLayout.getTabAt(position).select();
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String codigoAlumno = edtCodigoAlumno.getText().toString();

                ocultarTeclado();

                if(!codigoAlumno.equals("")){

                    mostrarHorasAlumno(codigoAlumno);

                }else {

                    Toast.makeText(getContext(), "Ingrese identificacion", Toast.LENGTH_SHORT).show();

                    edtCodigoAlumno.requestFocus();

                }

            }
        });


        //Si la app tiene guardado un codigo de cliente con SharedPreferences, realizamos la consulta
        SharedPreferences preferencias = getActivity().getSharedPreferences("codigoalumno", Context.MODE_PRIVATE);
        String getCodigo = preferencias.getString("codigo", "0");

        if(!getCodigo.equals("0")){

            edtCodigoAlumno.setText(getCodigo);
            mostrarHorasAlumno(getCodigo);

        }

        return vista;

    }


    //Metodo para mostrar los datos y horas del alumno
    private void mostrarHorasAlumno(String pCodigoAlumno){

        loadingBar.iniciarDialogCargando();

        String URL = dominioAmbiente + "/yolatinoapi/cliente/horas-cliente/" + pCodigoAlumno;

        System.out.println(URL);

        JsonObjectRequest jsonDatos = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Log.d("Response: ", response.toString());

                        DatosConsumoHoras consumoHoras;
                        listaArrayConsumoHoras.clear();

                        setCodigoSharedPreferences(pCodigoAlumno);

                        try {

                            String resultado = response.getString("resultado");

                            if(resultado.equals("ok")){

                                Toast.makeText(getContext(), "Alumno encontrado", Toast.LENGTH_SHORT).show();

                                JSONObject cliente = response.getJSONObject("cliente");
                                JSONArray jsonListaConsumos = response.getJSONArray("consumoHoras");
                                jsonListaCompraHoras = response.getJSONArray("compraHoras");


                                String nombreCliente = cliente.getString("NOMBRE_CLIENTE");
                                String horasVigentes = cliente.getString("HORAS_VIGENTES");

                                txvNombreAlumno.setText(nombreCliente);
                                txvHorasAlumno.setText(horasVigentes);

                                //Extraemos lista de consumo horas
                                if(jsonListaConsumos.length() > 0){

                                    for(int i = 0; i < jsonListaConsumos.length(); i++){

                                        JSONObject datoJson =new JSONObject(jsonListaConsumos.get(i).toString());

                                        String fechaConsumo = datoJson.getString("FECHA_CONSUMO");
                                        String nombreClase = datoJson.getString("NOMBRE_CLASE");
                                        String horasConsumo = datoJson.getString("HORAS_CONSUMO");

                                        consumoHoras = new DatosConsumoHoras(fechaConsumo, nombreClase, horasConsumo);

                                        listaArrayConsumoHoras.add(consumoHoras);

                                    }

                                    FrmHorasConsumo.getInstance().setearListaConsumos(listaArrayConsumoHoras);

                                }else{

                                    Toast.makeText(getContext(), "No se encontraron clases", Toast.LENGTH_SHORT).show();

                                    FrmHorasConsumo.getInstance().setearListaConsumos(listaArrayConsumoHoras);

                                }

                            }else {

                                txvNombreAlumno.setText("-");
                                txvHorasAlumno.setText("-");

                                FrmHorasConsumo.getInstance().setearListaConsumos(listaArrayConsumoHoras);

                                Toast.makeText(getContext(), "No se encontró alumno", Toast.LENGTH_SHORT).show();

                            }

                            loadingBar.cerrarDialogCargando();

                        }catch (JSONException e){

                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                            loadingBar.cerrarDialogCargando();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), "Error de Conexión", Toast.LENGTH_SHORT).show();

                        loadingBar.cerrarDialogCargando();

                    }
                });

        requestVolley.add(jsonDatos);
        requestVolley.getCache().clear();

    }

    private void setCodigoSharedPreferences(String codigoAlumno){

        SharedPreferences preferencias = getActivity().getSharedPreferences("codigoalumno", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("codigo", codigoAlumno);

        editor.commit();
    }

    public JSONArray getListaCompraHoras(){
        return jsonListaCompraHoras;
    }

    public void ocultarTeclado(){
        View vieww = getActivity().getCurrentFocus();
        if(vieww != null){
            //Aquí esta la magia
            InputMethodManager input = (InputMethodManager) (getActivity().getSystemService(Context.INPUT_METHOD_SERVICE));
            input.hideSoftInputFromWindow(vieww.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}