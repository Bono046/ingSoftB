package it.unibs.ing.model.user;

public class Configuratore implements IConfiguratore {

    private static final String USERNAME_PREDEFINITO = "user";
    private static final String PASSWORD_PREDEFINITO = "password";

    private final String username;
    private final String password;

    public Configuratore(String username, String password) {
        this.username = username;
        this.password = password;
    }


    @Override
    public Boolean verificaPrimoAccesso() {
        return this.username.equals(USERNAME_PREDEFINITO) && this.password.equals(PASSWORD_PREDEFINITO);
    }

    @Override
    public String getUsername() {
        return username;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            return true;
        }
    return false;
    }











}