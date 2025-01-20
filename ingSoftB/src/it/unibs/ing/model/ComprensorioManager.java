package it.unibs.ing.model;

import java.util.ArrayList;

public class ComprensorioManager {
    private ArrayList<ComprensorioGeografico> listaComprensori;
    
    public ComprensorioManager() {
        listaComprensori = new ArrayList<>();
    }
    
    public ArrayList<ComprensorioGeografico> getListaComprensori() {
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