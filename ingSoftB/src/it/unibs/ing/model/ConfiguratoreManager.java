package it.unibs.ing.model;

import java.util.ArrayList;

public class ConfiguratoreManager {

    private static ArrayList<Configuratore> listaConfiguratori;


    public ConfiguratoreManager() {
        listaConfiguratori = new ArrayList<>();
    }

    public static ArrayList<Configuratore> getListaConfiguratori() {
        return listaConfiguratori;
    }

    public static void addToListaConfiguratori(Configuratore conf) {
        listaConfiguratori.add(conf);
    }

    public static boolean userValido(String user) {
        for (Configuratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(user))
                return false;
        }
        return true;
    }


    public static boolean loginConfiguratore(String username, String password) {
        for (Configuratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(username) && conf.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
