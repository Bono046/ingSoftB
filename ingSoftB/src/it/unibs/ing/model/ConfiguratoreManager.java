package it.unibs.ing.model;

import java.util.ArrayList;

public class ConfiguratoreManager implements ManagerInterface {

    private ArrayList<Configuratore> listaConfiguratori;


    public ConfiguratoreManager() {
        listaConfiguratori = new ArrayList<>();
    }

    public ArrayList<Configuratore> getLista() {
        return listaConfiguratori;
    }

    public void addToListaConfiguratori(Configuratore conf) {
        listaConfiguratori.add(conf);
    }

    public boolean userValido(String user) {
        for (Configuratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(user))
                return false;
        }
        return true;
    }


    public boolean loginConfiguratore(String username, String password) {
        for (Configuratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(username) && conf.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }





}
