package it.unibs.ing.model.gerarchia;

import java.util.HashMap;
import java.util.Map;


public class CategoriaFoglia implements ICategoria {
    
	String nome;

	public CategoriaFoglia(String nome) {
        this.nome = nome;
    }

    
	@Override
	public boolean isFoglia() {
	    return true;
	}
	
	
	@Override
	public void aggiungiSottocategoria(String nome, ICategoria categoria) {
	    throw new UnsupportedOperationException("Le foglie non possono avere sottocategorie.");
	}
	
	public String getCampo() {
		return "";
	}
	
	
	public HashMap<String, String> getDominio() {
	    return null;
	}


	@Override
	public String getNome() {
		return this.nome;
	}


	@Override
	public HashMap<String, ICategoria> getSottocategorie() {
		throw new UnsupportedOperationException("Unimplemented method 'getSottocategorie'");
	}


	@Override
	public boolean isNomeUnivoco(String nome) {	
		if(this.nome.equals(nome)) {
			return false;
		}
		return true;
	}
	

	
	
}