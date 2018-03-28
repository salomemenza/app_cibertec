package com.example.salomon.aplicacionmovil.data.SQLite;

/**
 * Created by Salomon on 18/11/2017.
 */

public class Entidad {
    interface ColumnUsuario {
        String codigousuario = "codigousuario";
        String login = "login";
        String password = "password";
        String apellidopaterno = "apellidopaterno";
        String nombre = "nombre";
        String sexo = "sexo";
    }

    public static class Usuario implements ColumnUsuario {
        // Métodos auxiliares
    }
}
