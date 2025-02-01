package it.unibs.ing.model.user;

import it.unibs.ing.model.IManager;

public interface IConfiguratoreManager extends IManager<IConfiguratore> {

    boolean userValido(String user);
    boolean loginConfiguratore(String username, String password);

}