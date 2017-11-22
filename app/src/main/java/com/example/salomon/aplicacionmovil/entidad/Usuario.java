package com.example.salomon.aplicacionmovil.entidad;

/**
 * Created by Salomon on 18/11/2017.
 */

public class Usuario {
    private Integer codigoUsuario;
    private String login;
    private String password;
    private String apellidoPaterno;
    private String nombre;
    private Integer sexo;

    public Usuario() {
    }

    public Usuario(String apellidoPaterno, String nombre) {
        this.apellidoPaterno = apellidoPaterno;
        this.nombre = nombre;
    }

    public Usuario(Integer codigoUsuario,String login, String password, String apellidoPaterno, String nombre,Integer sexo) {
        this.codigoUsuario = codigoUsuario;
        this.login = login;
        this.password = password;
        this.apellidoPaterno = apellidoPaterno;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public Integer getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Integer codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }
}
