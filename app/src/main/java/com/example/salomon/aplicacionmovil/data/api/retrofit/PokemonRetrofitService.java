package com.example.salomon.aplicacionmovil.data.api.retrofit;

import com.example.salomon.aplicacionmovil.data.api.Constants;
import com.example.salomon.aplicacionmovil.data.api.client.PokemonRespuesta;
import com.example.salomon.aplicacionmovil.data.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonRetrofitService {
    @GET(Constants.LISTA_POKEMON)
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
