package it.unibs.ing.model.user;

import it.unibs.ing.model.comprensorio.IComprensorio;

public class Fruitore {

    private String username;
    private String password;
    private IComprensorio comprensiorio;
    private String mail;

    public Fruitore(String username, String password, IComprensorio comprensiorio, String mail) {
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


    public IComprensorio getComprensiorio() {
        return comprensiorio;
    }


    public String getMail() {
        return mail;
    }

}