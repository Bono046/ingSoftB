package it.unibs.ing.model;

import java.util.ArrayList;

public class propostaManager {
    private ArrayList<Proposta> listaProposte;
    private  ArrayList<StatoProposta> statiProposta;
    private enum StatoProposta {SOSPESO, APERTA, CHIUSA, RITIRATA};

    public propostaManager() {
        listaProposte= new ArrayList<>(); 
	    statiProposta= new ArrayList<>();
    }

    public  ArrayList<Proposta> getListaProposte() {
		return listaProposte;
	}
	
	public  void addProposta(Proposta p) {
		listaProposte.add(p);
	}


	public  void setListaProposte(ArrayList<Proposta> listaProposte) {
		for(Proposta p : listaProposte) {
			listaProposte.add(p);
		}
	}
	
	public  ArrayList<Proposta> getListaProposteUser(String user) {
		ArrayList<Proposta> lista = new ArrayList<>();
		for(Proposta p : listaProposte) {
			if(p.getUsername().equals(user))
				lista.add(p);
		}
		return lista;
	}

	public  ArrayList<Proposta> getProposteAperteFromUsers(ArrayList<String> listaUser) {
		ArrayList<Proposta> proposteFromComprensorio=new ArrayList<>();
		for(String user:listaUser) {
			for(Proposta p:listaProposte) {
				if(user.equals(p.getUsername()) && (p.isAperto())) {
					proposteFromComprensorio.add(p);
				}
			}
		}

		return proposteFromComprensorio;
	}


}
