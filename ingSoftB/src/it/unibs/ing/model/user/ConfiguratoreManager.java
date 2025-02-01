package it.unibs.ing.model.user;

import java.util.ArrayList;

public class ConfiguratoreManager implements IConfiguratoreManager {

    private ArrayList<IConfiguratore> listaConfiguratori;


    public ConfiguratoreManager() {
        listaConfiguratori = new ArrayList<>();
    }

    @Override
    public ArrayList<IConfiguratore> getLista() {
        return listaConfiguratori;
    }

    @Override
    public void addElemento(IConfiguratore conf) {
        listaConfiguratori.add(conf);
    }

    @Override
    public boolean userValido(String user) {
        for (IConfiguratore conf : listaConfiguratori) {
            if (conf.getUsername().equals(user))
                return false;
        }
        return true;
    }

    @Override
    public boolean loginConfiguratore(String username, String password) {
        for (IConfiguratore conf : listaConfiguratori) {
            if (conf.login(username, password)) {
                return true;
            }
        }
        return false;
    }





}
