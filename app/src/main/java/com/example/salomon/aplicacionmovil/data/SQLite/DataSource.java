package com.example.salomon.aplicacionmovil.data.SQLite;

/**
 * Created by Salomon on 18/11/2017.
 */

public class DataSource {
    // DECLARA LOS NOMBRES DE LAS TABLAS
    public static final String TB_USUARIO = "usuarios";

    //TIPOS DE DATOS
    public static final String TP_STRING = "text";
    public static final String TP_INT = "integer";
    public static final String TP_NUMERIC = "real";

    //CREACIÓN DE LA TABLA
    public static final String CREATE_USUARIO =
        "create table IF NOT EXISTS "+ TB_USUARIO +"(" +
                Entidad.Usuario.codigousuario                + " " + TP_INT              + " PRIMARY KEY AUTOINCREMENT NOT NULL," +
                Entidad.Usuario.login                        + " " + TP_STRING           + " NOT NULL," +
                Entidad.Usuario.password                     + " " + TP_STRING           + " NOT NULL," +
                Entidad.Usuario.apellidopaterno              + " " + TP_STRING           + " NOT NULL," +
                Entidad.Usuario.nombre                       + " " + TP_STRING           + " NOT NULL," +
                Entidad.Usuario.sexo                         + " " + TP_INT              + " NULL) ";

    public static final String DELETE_USUARIO =
            "DROP TABLE IF EXISTS " + TB_USUARIO;

}
