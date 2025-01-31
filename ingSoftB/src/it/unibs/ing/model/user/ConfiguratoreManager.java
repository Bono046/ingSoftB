package it.unibs.ing.model.user;

import java.util.ArrayList;

public class ConfiguratoreManager {

    private ArrayList<IConfiguratore> listaConfiguratori;


    public ConfiguratoreManager() {
        listaConfiguratori = new ArrayList<>();
    }

    public ArrayList<IConfiguratore> getLista() {
        return listaConfiguratori;
    }

    public void addToListaConfiguratori(IConfiguratore conf) {
        listaConfiguratori.add(conf);
    }

    public boolean userValido(String user) {
        for (IConfiguratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(user))
                return false;
        }
        return true;
    }

    public boolean loginConfiguratore(String username, String password) {
        for (IConfiguratore conf : listaConfiguratori) {
            if (conf.login(username, password)) {
                return true;
            }
        }
        return false;
    }





}
