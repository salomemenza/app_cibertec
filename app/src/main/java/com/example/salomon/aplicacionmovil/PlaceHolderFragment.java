package com.example.salomon.aplicacionmovil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    public static final String TAG = "PlaceHolderFragment";

    public static PlaceHolderFragment newInstance(Bundle arguments) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        if( arguments != null ){
            fragment.setArguments(arguments);
        }
        //getArguments().toString("ef");
        Log.i(TAG, String.valueOf(arguments));
        Log.i(TAG, "new instance");
        // args.putString(ARG_SECTION_TITLE, sectionTitle);
        // fragment.setArguments(args);
        return fragment;
    }

    public PlaceHolderFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_place_holder_fragment, container, false);

        super.onCreateView(inflater, container, savedInstanceState);
        View v =  inflater.inflate(R.layout.fragment_place_holder_fragment, container, false);
        //return v;

        // Ubicar argumento en el text view de section_fragment.xml

        String title = getArguments().getString("placeholderTitle");
        TextView textTitulo = (TextView) v.findViewById(R.id.title);
        textTitulo.setText(title);
        Log.i(TAG,"OLAAAAAAAAA");
        Log.i(TAG,title);
        return v;
    }

}