package it.unibs.ing.model;

import java.util.ArrayList;

public class ComprensorioManager implements ManagerInterface {
    
    private ArrayList<ComprensorioGeografico> listaComprensori;
    
    public ComprensorioManager() {
        listaComprensori = new ArrayList<>();
    }
    
    
    public ArrayList<ComprensorioGeografico> getLista() {
        return listaComprensori;
    }

    public void setListaComprensori(ArrayList<ComprensorioGeografico> lista) {
        for(ComprensorioGeografico c : lista) {
            listaComprensori.add(c);
        }
    }

    public void addComprensorio(ComprensorioGeografico comprensorio) {
        listaComprensori.add(comprensorio);
        
    }
}