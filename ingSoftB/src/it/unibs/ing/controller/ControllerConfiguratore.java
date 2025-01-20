package it.unibs.ing.controller;
import it.unibs.ing.model.Configuratore;
import it.unibs.ing.model.ConfiguratoreManager;

public class ControllerConfiguratore {
    ConfiguratoreManager configuratoreManager;
    
    public ControllerConfiguratore() {
        configuratoreManager = new ConfiguratoreManager();
    }

    public Boolean verificaCredenzialiPrimoAccesso(String usernamePredefinito, String passwordPredefinita) {
        return Configuratore.verificaPrimoAccesso(usernamePredefinito, passwordPredefinita);
    }

    public Boolean userOk(String username) {
        return configuratoreManager.userValido(username);
    }

    public void registraConfiguratore(String username, String password) {
        Configuratore conf = new Configuratore(username, password);
        configuratoreManager.addToListaConfiguratori(conf);
    }

    public Boolean loginConfiguratore(String username, String password) {
        return configuratoreManager.loginConfiguratore(username, password);
    }

}
