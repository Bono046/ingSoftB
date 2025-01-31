package it.unibs.ing.model.user;

import it.unibs.ing.model.comprensorio.IComprensorio;

public class Fruitore implements IFruitore {

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


    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
    return false;
    }


    public IComprensorio getComprensiorio() {
        return comprensiorio;
    }


    public String getMail() {
        return mail;
    }

}