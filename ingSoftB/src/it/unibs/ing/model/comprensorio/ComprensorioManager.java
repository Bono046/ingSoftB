package it.unibs.ing.model.comprensorio;

import java.util.ArrayList;



public class ComprensorioManager implements IComprensorioManager {
    
    private ArrayList<IComprensorio> listaComprensori;
    
    public ComprensorioManager() {
        listaComprensori = new ArrayList<>();
    }
    
    @Override
    public ArrayList<IComprensorio> getLista() {
        return listaComprensori;
    }

    @Override   
    public void addElemento(IComprensorio comprensorio) {
        listaComprensori.add(comprensorio);
    }



}