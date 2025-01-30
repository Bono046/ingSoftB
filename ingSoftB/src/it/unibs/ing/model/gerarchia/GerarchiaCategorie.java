package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;
import java.util.Stack;

public class GerarchiaCategorie implements IGerarchia {
	private ICategoria radice;
	private ICategoria categoriaCorrente;
    private ArrayList<ICategoria> listaFoglie = new ArrayList<>();
    private Stack<ICategoria> percorso;
    
	public GerarchiaCategorie(ICategoria radice) {
		this.radice = radice;
        this.percorso = new Stack<>();
        this.categoriaCorrente = radice;
    }
	
	@Override
    public ICategoria getCategoriaRadice() {
		return this.radice;
	}
	
   @Override
public ArrayList<ICategoria> getListaFoglie() {
		return listaFoglie;
	}

	@Override
    public void setListaFoglie(ArrayList<ICategoria> c) {
		
		for(ICategoria foglia: c) {
            //if(foglia.isFoglia())
			this.listaFoglie.add(foglia);
        }
	}
	
	@Override
    public void addToListaFoglie(ICategoria c) {
        //if(foglia.isFoglia())
		this.listaFoglie.add(c);
	}
	
    @Override
    public ICategoria getCategoriaCorrente() {
        return categoriaCorrente;
    }
    
    @Override
    public void setCategoriaCorrente() { 	
    		categoriaCorrente = radice;
    }
    
    
    @Override
    public boolean vaiASottocategoria(ICategoria sottocategoria) {
        if (categoriaCorrente.getSottocategorie() != null &&
            categoriaCorrente.getSottocategorie().containsValue(sottocategoria)) {
            percorso.push(categoriaCorrente);
            categoriaCorrente = sottocategoria;
            return true;
        }
        return false;
    }
    
    
    @Override
    public Stack<ICategoria> getPercorso() {
		return percorso;
	}

	
    @Override
    public boolean tornaIndietro() {
        if (!percorso.isEmpty()) {
            categoriaCorrente = percorso.pop();
            return true;
        }
        return false; 
    }

	
    
    @Override
    public ICategoria getFogliaDaNome(String nome) {
    	for(ICategoria c: listaFoglie) {
    		if(c.getNome().equals(nome))
    			return c;
    	}
    	return null;
    }
    

    
    
    
}
