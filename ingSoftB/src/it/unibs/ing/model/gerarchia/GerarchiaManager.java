package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;

public class GerarchiaManager implements IGerarchiaManager {

    private ArrayList<IGerarchia> listaOggettiGerarchia;

    public GerarchiaManager() {
        listaOggettiGerarchia = new ArrayList<>();
    }

    @Override
	public ArrayList<IGerarchia> getLista() {
		return listaOggettiGerarchia;
	}

	
	@Override
	public void addElemento(IGerarchia g) {
		listaOggettiGerarchia.add(g);
	}
    
    @Override
	public ArrayList<ICategoria> getListaRadici() {
    	ArrayList<ICategoria> listaRadici = new ArrayList<>(); 
    	
    	for(IGerarchia c : listaOggettiGerarchia) {
    		listaRadici.add(c.getCategoriaRadice());
    	}
    	return listaRadici;
    }


    @Override
	public boolean checkNomeGerarchia(String nome) {
    	for(ICategoria c : getListaRadici()) {
    		if(c.getNome().equals(nome))
    			return false;
    	}
    	return true;
    }


    
}
