package it.unibs.ing.model.user;

import java.util.ArrayList;

import it.unibs.ing.model.IManager;
import it.unibs.ing.model.comprensorio.IComprensorio;

public interface IFruitoreManager extends IManager<IFruitore> {


    boolean userValido(String username);
    boolean loginFruitore(String username, String password);
    IComprensorio getComprensorioFromUser(String user);
    ArrayList<String> getUserFruitoriFromComprensorio(IComprensorio c);

}