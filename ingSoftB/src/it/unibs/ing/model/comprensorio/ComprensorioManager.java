package it.unibs.ing.model.comprensorio;

import java.util.ArrayList;

import it.unibs.ing.model.IManager;

public class ComprensorioManager implements IManager<IComprensorio> {
    
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