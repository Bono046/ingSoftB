package it.unibs.ing.model;

import java.util.ArrayList;

public class Fruitore {

    private String username;
    private String password;
    private ComprensorioGeografico comprensiorio;
    private String mail;

    public Fruitore(String username, String password, ComprensorioGeografico comprensiorio, String mail) {
        this.username = username;
        this.password = password;
        this.comprensiorio = comprensiorio;
        this.mail = mail;
    }


    public String getUsername() {
        return username;
    }


    protected String getPassword() {
        return password;
    }


    public ComprensorioGeografico getComprensiorio() {
        return comprensiorio;
    }


    public String getMail() {
        return mail;
    }

}