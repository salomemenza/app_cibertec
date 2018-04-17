package com.example.salomon.aplicacionmovil.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.data.model.Pokemon;

import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {
    private ArrayList<Pokemon> dataset;
    private Context context;
    private PokemonAdapterListener listener;

    public ListaPokemonAdapter(Context context, PokemonAdapterListener listener) {
        this.context = context;
        dataset = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Pokemon p = dataset.get(position);
        holder.nombreTextView.setText(p.getName());

        //String formatted = String.format("%03d", p.getNumber());
        String formatted = convertPokemonNumber(p.getNumber());
        Glide.with(context)
                .load("https://www.serebii.net/pokemongo/pokemon/" + formatted + ".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.fotoImageView);

        clickEvents(holder,position,p);
    }

    private String convertPokemonNumber(Integer number){
        return String.format("%03d", number );
    };

    private void clickEvents(ViewHolder holder, final int position, final Pokemon p){
        holder.pokemonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPokemonRowClicked(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private LinearLayout pokemonContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            pokemonContainer = (LinearLayout) itemView.findViewById(R.id.lytItem);
            fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
            nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
        }
    }

    public interface PokemonAdapterListener {
        void onPokemonRowClicked(Pokemon pokemon);
    }
}
