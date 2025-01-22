package it.unibs.ing.model;

public class Configuratore {

    private static final String USERNAME_PREDEFINITO = "user";
    private static final String PASSWORD_PREDEFINITO = "password";

    private final String username;
    private final String password;

    public Configuratore(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public static Boolean verificaPrimoAccesso(String user, String pass) {
        return user.equals(USERNAME_PREDEFINITO) && pass.equals(PASSWORD_PREDEFINITO);
    }

    public String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }











}