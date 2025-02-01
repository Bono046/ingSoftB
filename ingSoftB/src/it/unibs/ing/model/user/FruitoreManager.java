package it.unibs.ing.model.user;

import java.util.ArrayList;

import it.unibs.ing.model.comprensorio.IComprensorio;

public class FruitoreManager implements IFruitoreManager {
    private ArrayList<IFruitore> listaFruitori;

    public FruitoreManager() {
        listaFruitori = new ArrayList<>();
    }

    @Override
    public ArrayList<IFruitore> getLista() {
        return listaFruitori;
    }

    @Override
    public void addElemento(IFruitore f) {
        listaFruitori.add(f);
    }

    @Override
    public boolean userValido(String username) {
        for (IFruitore user : listaFruitori) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    @Override
    public boolean loginFruitore(String username, String password) {
        for (IFruitore user : listaFruitori) {
            if (user.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public IComprensorio getComprensorioFromUser(String user) {
        for(IFruitore f : listaFruitori) {
            if(f.getUsername().equals(user))
                return f.getComprensiorio();
        }
        return null;
    }

    @Override
    public ArrayList<String> getUserFruitoriFromComprensorio(IComprensorio c){
        ArrayList<String> lista = new ArrayList<>();

        for(IFruitore f : listaFruitori) {
            if(f.getComprensiorio().getNome().equals(c.getNome()))
                lista.add(f.getUsername());
        }
        return lista;
    }






}
