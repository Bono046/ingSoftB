package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;

public class GerarchiaManager {

    private ArrayList<IGerarchia> listaOggettiGerarchia;

    public GerarchiaManager() {
        listaOggettiGerarchia = new ArrayList<>();
    }

    public ArrayList<IGerarchia> getListaOggettiGerarchia() {
		return listaOggettiGerarchia;
	}

	public void setListaOggettiGerarchia(ArrayList<IGerarchia> gerarchie) {
		listaOggettiGerarchia = gerarchie;
	}
	
	public void addGerarchia(IGerarchia g) {
		listaOggettiGerarchia.add(g);
	}
    
    public ArrayList<ICategoria> getListaRadici() {
    	ArrayList<ICategoria> listaRadici = new ArrayList<>(); 
    	
    	for(IGerarchia c : listaOggettiGerarchia) {
    		listaRadici.add(c.getCategoriaRadice());
    	}
    	return listaRadici;
    }


    public boolean checkNomeGerarchia(String nome) {
    	for(ICategoria c : getListaRadici()) {
    		if(c.getNome().equals(nome))
    			return false;
    	}
    	return true;
    }


    
}
