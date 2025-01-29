package it.unibs.ing.model;


import java.util.ArrayList;

public class PropostaManager {

    ArrayList<Proposta> listaProposte;
	ChiusuraProposteStrategy chiusuraProposteStrategy;

    public PropostaManager() {
        listaProposte = new ArrayList<Proposta>();
    }

    public ArrayList<Proposta> getListaProposte() {
		return listaProposte;
	}
	
	public void addProposta(Proposta p) {
		listaProposte.add(p);
	}


	public void setListaProposte(ArrayList<Proposta> listaProposte) {
		for(Proposta p : listaProposte) {
			listaProposte.add(p);
		}
	}

	public void setChiusuraProposteStrategy(ChiusuraProposteStrategy chiusuraProposteStrategy) {
		this.chiusuraProposteStrategy = chiusuraProposteStrategy;
	}

	public boolean verificaProposta(Proposta proposta, ArrayList<Proposta> listaProposte) {
		return chiusuraProposteStrategy.execute(proposta, listaProposte);
	}

    
	
	public ArrayList<Proposta> getListaProposteUser(String user) {
		ArrayList<Proposta> lista = new ArrayList<>();
		for(Proposta p : listaProposte) {
			if(p.getUsername().equals(user))
				lista.add(p);
		}
		return lista;
	}

	public ArrayList<Proposta> getListaProposteAperteUser(String user) {
		ArrayList<Proposta> lista = new ArrayList<>();
		for(Proposta p : listaProposte) {
			if(p.getUsername().equals(user)){
				if(p.isAperto()) {
					lista.add(p);
				}
			}
		}
		return lista;
	}

	public ArrayList<Proposta> getProposteAperteFromUsers(ArrayList<String> listaUser) {
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
	