package it.unibs.ing.model.proposta;


import java.util.ArrayList;

public class PropostaManager {

    ArrayList<IProposta> listaProposte;
	IChiusuraProposteStrategy chiusuraProposteStrategy;

    public PropostaManager() {
        listaProposte = new ArrayList<IProposta>();
    }

    public ArrayList<IProposta> getListaProposte() {
		return listaProposte;
	}
	
	public void addProposta(IProposta p) {
		listaProposte.add(p);
	}


	public void setListaProposte(ArrayList<IProposta> listaProposte) {
		for(IProposta p : listaProposte) {
			listaProposte.add(p);
		}
	}

	public void setChiusuraProposteStrategy(IChiusuraProposteStrategy chiusuraProposteStrategy) {
		this.chiusuraProposteStrategy = chiusuraProposteStrategy;
	}

	public boolean verificaProposta(IProposta proposta, ArrayList<IProposta> listaProposte) {
		return chiusuraProposteStrategy.execute(proposta, listaProposte);
	}

    
	
	public ArrayList<IProposta> getListaProposteUser(String user) {
		ArrayList<IProposta> lista = new ArrayList<>();
		for(IProposta p : listaProposte) {
			if(p.getUsername().equals(user))
				lista.add(p);
		}
		return lista;
	}

	public ArrayList<IProposta> getListaProposteAperteUser(String user) {
		ArrayList<IProposta> lista = new ArrayList<>();
		for(IProposta p : listaProposte) {
			if(p.getUsername().equals(user)){
				if(p.isAperto()) {
					lista.add(p);
				}
			}
		}
		return lista;
	}

	public ArrayList<IProposta> getProposteAperteFromUsers(ArrayList<String> listaUser) {
		ArrayList<IProposta> proposteFromComprensorio=new ArrayList<>();
		for(String user:listaUser) {
			for(IProposta p:listaProposte) {
				if(user.equals(p.getUsername()) && (p.isAperto())) {
					proposteFromComprensorio.add(p);
				}
			}
		}
		return proposteFromComprensorio;
	}


}
	