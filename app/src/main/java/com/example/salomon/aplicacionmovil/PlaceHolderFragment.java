package com.example.salomon.aplicacionmovil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragmento para el contenido principal
 */
public class PlaceHolderFragment extends Fragment {
    /**
     * Este argumento del fragmento representa el título de cada
     * sección
     */
    public static final String TAG = "section_number";

    public static PlaceHolderFragment newInstance(Bundle arguments) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        if( arguments != null ){
            fragment.setArguments(args);
        }
        // args.putString(ARG_SECTION_TITLE, sectionTitle);
        // fragment.setArguments(args);
        return fragment;
    }

    public PlaceHolderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_holder_fragment, container, false);

        // Ubicar argumento en el text view de section_fragment.xml
        String title = getArguments().getString(TAG);
        TextView titulo = (TextView) view.findViewById(R.id.title);
        titulo.setText(title);
        return view;
    }

}