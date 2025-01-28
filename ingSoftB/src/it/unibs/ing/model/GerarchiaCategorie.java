package it.unibs.ing.model;

import java.util.ArrayList;
import java.util.Stack;

public class GerarchiaCategorie {
	private ComponenteCategoria radice;
	private ComponenteCategoria categoriaCorrente;
    private ArrayList<ComponenteCategoria> listaFoglie = new ArrayList<>();
    private Stack<ComponenteCategoria> percorso;
    
	public GerarchiaCategorie(ComponenteCategoria radice) {
		this.radice = radice;
        this.percorso = new Stack<>();
        this.categoriaCorrente = radice;
    }
	
	public ComponenteCategoria getCategoriaRadice() {
		return this.radice;
	}
	
   public ArrayList<ComponenteCategoria> getListaFoglie() {
		return listaFoglie;
	}

	public void setListaFoglie(ArrayList<ComponenteCategoria> c) {
		
		for(ComponenteCategoria foglia: c) {
            //if(foglia.isFoglia())
			this.listaFoglie.add(foglia);
        }
	}
	
	public void addToListaFoglie(ComponenteCategoria c) {
        //if(foglia.isFoglia())
		this.listaFoglie.add(c);
	}
	
    public ComponenteCategoria getCategoriaCorrente() {
        return categoriaCorrente;
    }
    
    public void setCategoriaCorrente() { 	
    		categoriaCorrente = radice;
    }
    
    
    public boolean vaiASottocategoria(ComponenteCategoria sottocategoria) {
        if (categoriaCorrente.getSottocategorie() != null &&
            categoriaCorrente.getSottocategorie().containsValue(sottocategoria)) {
            percorso.push(categoriaCorrente);
            categoriaCorrente = sottocategoria;
            return true;
        }
        return false;
    }
    
    
    public Stack<ComponenteCategoria> getPercorso() {
		return percorso;
	}

	
    public boolean tornaIndietro() {
        if (!percorso.isEmpty()) {
            categoriaCorrente = percorso.pop();
            return true;
        }
        return false; 
    }

	
    
    public ComponenteCategoria getFogliaDaNome(String nome) {
    	for(ComponenteCategoria c: listaFoglie) {
    		if(c.getNome().equals(nome))
    			return c;
    	}
    	return null;
    }
    

    
    
    
}
