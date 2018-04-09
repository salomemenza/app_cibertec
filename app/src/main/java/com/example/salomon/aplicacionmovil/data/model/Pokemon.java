package com.example.salomon.aplicacionmovil.data.model;

public class Pokemon {
    private Integer number;
    private String name;
    private String url;

    public Pokemon() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getNumber() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
