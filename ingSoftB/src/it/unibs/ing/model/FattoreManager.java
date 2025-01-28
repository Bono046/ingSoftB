package it.unibs.ing.model;

import java.util.ArrayList;

public class FattoreManager {
    
    private ArrayList<FattoreConversione> listaFattori = new ArrayList<>();
    

    public ArrayList<FattoreConversione> getListaFattori() {
		return listaFattori;
	}
	
	
	public void addFattore (String c1, String c3, double f13) {
		
		FattoreConversione fattore = new FattoreConversione(c1, c3, f13);
		FattoreConversione f_inverso = fattore.creaFattoreInverso();
		
		 ArrayList<FattoreConversione> fattoriDaAggiungere = new ArrayList<>();
		 double valoreFattore;
		 
		if(listaFattori.isEmpty()) {		//prima iterazione
			listaFattori.add(fattore);
			listaFattori.add(f_inverso);
		} else {
			for(FattoreConversione fact : listaFattori) {
				String c2 = fact.getC2();
				if(fact.getC1().equals(c1)) {
					if(!esisteFattore(c3, c2)) {
						valoreFattore = Math.round((f13 / fact.getFattore()) * 100.0) / 100.0;
						valoreFattore = Math.max(0.5, Math.min(2.0, valoreFattore));
						FattoreConversione newFattore = new FattoreConversione(c2, c3, valoreFattore);
						fattoriDaAggiungere.add(newFattore);
						fattoriDaAggiungere.add(newFattore.creaFattoreInverso());
					}
				}
				if(fact.getC1().equals(c3)) {
					if(!esisteFattore(c1, c2)) {
						valoreFattore = Math.round((f13 * fact.getFattore()) * 100.0) / 100.0;
						valoreFattore = Math.max(0.5, Math.min(2.0, valoreFattore));
						FattoreConversione newFattore = new FattoreConversione(c1, c2, valoreFattore);
						fattoriDaAggiungere.add(newFattore);
						fattoriDaAggiungere.add(newFattore.creaFattoreInverso());
					}
			}}
			listaFattori.add(fattore);
			listaFattori.add(f_inverso);
			listaFattori.addAll(fattoriDaAggiungere);
			
		}
	}
	
	
	public Boolean esisteFattore(String c1, String c2) {
		
		for(FattoreConversione f: listaFattori) {
			String foglia1 = f.getC1();
			String foglia2 = f.getC2();
			
			if(foglia1.equals(c1) && foglia2.equals(c2))
				return true;
			if(foglia1.equals(c2) && foglia2.equals(c1))
				return true;
		}
		return false;
	}


    public  ArrayList<FattoreConversione> trovaFattore(String s) {
		ArrayList<FattoreConversione> fattoriDaVisualizzare = new ArrayList<>();
		for(FattoreConversione f: listaFattori) {
			if( f.getC1().equals(s) || f.getC2().equals(s) )
				fattoriDaVisualizzare.add(f);
		}
		return fattoriDaVisualizzare;
	}
	
	
	public FattoreConversione trovaFattore(String foglia1, String foglia2) {
		for(FattoreConversione f: listaFattori) {
			if( f.getC1().equals(foglia1) && f.getC2().equals(foglia2) )
				return f;
		}	
		return null;
	}


}
