package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;
import java.util.Stack;

public class GerarchiaCategorie {
	private ICategoria radice;
	private ICategoria categoriaCorrente;
    private ArrayList<ICategoria> listaFoglie = new ArrayList<>();
    private Stack<ICategoria> percorso;
    
	public GerarchiaCategorie(ICategoria radice) {
		this.radice = radice;
        this.percorso = new Stack<>();
        this.categoriaCorrente = radice;
    }
	
	public ICategoria getCategoriaRadice() {
		return this.radice;
	}
	
   public ArrayList<ICategoria> getListaFoglie() {
		return listaFoglie;
	}

	public void setListaFoglie(ArrayList<ICategoria> c) {
		
		for(ICategoria foglia: c) {
            //if(foglia.isFoglia())
			this.listaFoglie.add(foglia);
        }
	}
	
	public void addToListaFoglie(ICategoria c) {
        //if(foglia.isFoglia())
		this.listaFoglie.add(c);
	}
	
    public ICategoria getCategoriaCorrente() {
        return categoriaCorrente;
    }
    
    public void setCategoriaCorrente() { 	
    		categoriaCorrente = radice;
    }
    
    
    public boolean vaiASottocategoria(ICategoria sottocategoria) {
        if (categoriaCorrente.getSottocategorie() != null &&
            categoriaCorrente.getSottocategorie().containsValue(sottocategoria)) {
            percorso.push(categoriaCorrente);
            categoriaCorrente = sottocategoria;
            return true;
        }
        return false;
    }
    
    
    public Stack<ICategoria> getPercorso() {
		return percorso;
	}

	
    public boolean tornaIndietro() {
        if (!percorso.isEmpty()) {
            categoriaCorrente = percorso.pop();
            return true;
        }
        return false; 
    }

	
    
    public ICategoria getFogliaDaNome(String nome) {
    	for(ICategoria c: listaFoglie) {
    		if(c.getNome().equals(nome))
    			return c;
    	}
    	return null;
    }
    

    
    
    
}
