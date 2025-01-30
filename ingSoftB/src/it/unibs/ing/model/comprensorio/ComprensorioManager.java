package it.unibs.ing.model.comprensorio;

import java.util.ArrayList;

public class ComprensorioManager {
    
    private ArrayList<IComprensorio> listaComprensori;
    
    public ComprensorioManager() {
        listaComprensori = new ArrayList<>();
    }
    
    
    public ArrayList<IComprensorio> getLista() {
        return listaComprensori;
    }

    public void setListaComprensori(ArrayList<IComprensorio> lista) {
        for(IComprensorio c : lista) {
            listaComprensori.add(c);
        }
    }

    public void addComprensorio(IComprensorio comprensorio) {
        listaComprensori.add(comprensorio);
        
    }
}