package com.example.salomon.aplicacionmovil.data.api.client;

import com.example.salomon.aplicacionmovil.data.model.Pokemon;

import java.util.ArrayList;

public class PokemonRespuesta {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
