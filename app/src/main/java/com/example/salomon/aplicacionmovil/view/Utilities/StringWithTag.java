package com.example.salomon.aplicacionmovil.view.Utilities;

/**
 * Created by Salomon on 22/11/2017.
 */

public class StringWithTag {
    public String string;
    public Object tag;

    public StringWithTag(String string, Object tag) {
        this.string = string;
        this.tag = tag;
    }

    @Override
    public String toString() {
        return string;
    }
}
