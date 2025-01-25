package it.unibs.ing.model;

import java.util.ArrayList;

public class GerarchiaManager {

    private ArrayList<GerarchiaCategorie> listaOggettiGerarchia;

    public GerarchiaManager() {
        listaOggettiGerarchia = new ArrayList<>();
    }

    public ArrayList<GerarchiaCategorie> getListaOggettiGerarchia() {
		return listaOggettiGerarchia;
	}

	public void setListaOggettiGerarchia(ArrayList<GerarchiaCategorie> gerarchie) {
		listaOggettiGerarchia = gerarchie;
	}
	
	public void addGerarchia(GerarchiaCategorie g) {
		listaOggettiGerarchia.add(g);
	}
    
    public ArrayList<Categoria> getListaRadici() {
    	ArrayList<Categoria> listaRadici = new ArrayList<>(); 
    	
    	for(GerarchiaCategorie c : listaOggettiGerarchia) {
    		listaRadici.add(c.getCategoriaRadice());
    	}
    	return listaRadici;
    }


    public boolean checkNomeGerarchia(String nome) {
    	for(Categoria c : getListaRadici()) {
    		if(c.getNome().equals(nome))
    			return false;
    	}
    	return true;
    }


    
}
