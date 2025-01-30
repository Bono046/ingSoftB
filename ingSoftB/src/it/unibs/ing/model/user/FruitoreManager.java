package it.unibs.ing.model.user;

import java.util.ArrayList;

import it.unibs.ing.model.comprensorio.IComprensorio;

public class FruitoreManager {
    private ArrayList<Fruitore> listaFruitori;

    public FruitoreManager() {
        listaFruitori = new ArrayList<>();
    }

    public ArrayList<Fruitore> getLista() {
        return listaFruitori;
    }

    public void addToListaFruitori(Fruitore f) {
        listaFruitori.add(f);
    }

    public boolean userValido(String username) {
        for (Fruitore user : listaFruitori) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public boolean loginFruitore(String username, String password) {
        for (Fruitore user : listaFruitori) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public IComprensorio getComprensorioFromUser(String user) {
        for(Fruitore f : listaFruitori) {
            if(f.getUsername().equals(user))
                return f.getComprensiorio();
        }
        return null;
    }

    public ArrayList<String> getUserFruitoriFromComprensorio(IComprensorio c){
        ArrayList<String> lista = new ArrayList<>();

        for(Fruitore f : listaFruitori) {
            if(f.getComprensiorio().getNome().equals(c.getNome()))
                lista.add(f.getUsername());
        }
        return lista;
    }






}
