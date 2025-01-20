package it.unibs.ing.model;

import java.util.ArrayList;

public class FruitoreManager {
    private ArrayList<Fruitore> listaFruitori;

    public FruitoreManager() {
        listaFruitori = new ArrayList<>();
    }

    public ArrayList<Fruitore> getListaFruitori() {
        return listaFruitori;
    }

    public void addToListaConfiguratori(Fruitore f) {
        listaFruitori.add(f);
    }

    public boolean userValido(String username) {
        for (Fruitore user : listaFruitori) {
            if (user.getUsername().equals(username))
                return false;
        }
        return true;
    }

    public Fruitore loginFruitore(String username, String password) {
        for (Fruitore user : listaFruitori) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public ComprensorioGeografico getComprensorioFromUser(String user) {
        for(Fruitore f : listaFruitori) {
            if(f.getUsername().equals(user))
                return f.getComprensiorio();
        }
        return null;
    }

    public ArrayList<String> getUserFruitoriFromComprensorio(ComprensorioGeografico c){
        ArrayList<String> lista = new ArrayList<>();

        for(Fruitore f : listaFruitori) {
            if(f.getComprensiorio().getNome().equals(c.getNome()))
                lista.add(f.getUsername());
        }
        return lista;
    }






}
