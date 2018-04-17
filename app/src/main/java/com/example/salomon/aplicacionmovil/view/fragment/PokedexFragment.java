package com.example.salomon.aplicacionmovil.view.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.data.api.client.PokemonRespuesta;
import com.example.salomon.aplicacionmovil.data.api.retrofit.PokemonRetrofitClient;
import com.example.salomon.aplicacionmovil.data.api.retrofit.PokemonRetrofitService;
import com.example.salomon.aplicacionmovil.data.model.Pokemon;
import com.example.salomon.aplicacionmovil.view.Utilities.MyAlert;
import com.example.salomon.aplicacionmovil.view.activity.PokemonActivity;
import com.example.salomon.aplicacionmovil.view.adapter.ListaPokemonAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokedexFragment extends Fragment implements ListaPokemonAdapter.PokemonAdapterListener {

    //@BindView(R.id.toolbar) Toolbar toolbar;

    private Retrofit mRetrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private ProgressDialog mensajeBuilder;
    private MyAlert myAlert;

    private int offset;
    private boolean aptoParaCargar;
    private static final String TAG = "POKEDEX";

    private LinearLayoutManager mLayoutManager;

    public PokedexFragment(){
    }

    public static PokedexFragment newInstance(String param1, String param2) {
        PokedexFragment fragment = new PokedexFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pokedex);
        mensajeBuilder = new ProgressDialog(getActivity().getApplicationContext());
        myAlert = new MyAlert(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_pokedex, container, false);
        mensajeBuilder = new ProgressDialog(getActivity());

        // Replace 'android.R.id.list' with the 'id' of your RecyclerView
        recyclerView = view.findViewById(R.id.pokemon_recycler);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        Log.d("debugMode", "The application stopped after this");
        recyclerView.setLayoutManager(mLayoutManager);

        listaPokemonAdapter = new ListaPokemonAdapter(this.getActivity().getApplicationContext(),this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);

        final GridLayoutManager layoutManager = new GridLayoutManager(this.getActivity().getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        aptoParaCargar = true;
        offset = 0;
        mRetrofit = PokemonRetrofitClient.getClient();
        obtenerDatos(offset);

        return view;
    }

    private void obtenerDatos(int offset) {
        showProgress();

        PokemonRetrofitService pokemonService = mRetrofit.create(PokemonRetrofitService.class);
        Call<PokemonRespuesta> pokemonCall = pokemonService.obtenerListaPokemon(20, offset);

        pokemonCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful()){
                    PokemonRespuesta respuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = respuesta.getResults();
                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);
                }else{
                    myAlert.showErrorMessage(response.errorBody().toString());
                    //Snackbar.make(getActivity().findViewById(R.id.lytPokedex),response.errorBody().toString(),Snackbar.LENGTH_LONG).show();
                    //Log.e(TAG, " onResponse: " + response.errorBody());
                }
                hideProgress();
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                hideProgress();
                aptoParaCargar = true;
                //Log.e(TAG, " onFailure: " + t.getMessage());
                myAlert.showErrorMessage(t.getMessage());
                //Snackbar.make(getActivity().findViewById(R.id.lytPokedex),t.getMessage(),Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void showProgress() {
        mensajeBuilder.setTitle("Obteniendo información del servidor");
        mensajeBuilder.setMessage("Por favor espere");
        mensajeBuilder.setCancelable(false);
        mensajeBuilder.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mensajeBuilder.show();
    }

    public void hideProgress() {
        mensajeBuilder.dismiss();
    }

    @Override
    public void onPokemonRowClicked(Pokemon pokemon) {
        Intent i = new Intent(getActivity(),PokemonActivity.class);
        i.putExtra("titulo", pokemon.getName());
        i.putExtra("numero", String.format("%03d", pokemon.getNumber()));
        Log.i(TAG,pokemon.getNumber().toString());
        getActivity().startActivity(i);
    }
}
