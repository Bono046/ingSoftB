package it.unibs.ing.model;

import java.util.HashMap;


public class CategoriaFoglia extends Categoria {
    public CategoriaFoglia(String nome) {
        super(nome, null, null);
        this.sottocategorie = null;
    }

    
	@Override
	public boolean isFoglia() {
	    return true;
	}
	
	
	// Override del metodo per evitare l'aggiunta di sottocategorie
	@Override
	public void aggiungiSottocategoria(String nome, Categoria categoria) {
	    throw new UnsupportedOperationException("Le foglie non possono avere sottocategorie.");
	}
	
	@Override
	public String getCampo() {
	    return null; // Campo non applicabile per le foglie
	}
	
	@Override
	public HashMap<String, String> getDominio() {
	    return null; // Dominio non applicabile per le foglie
	}
	

	
	
}