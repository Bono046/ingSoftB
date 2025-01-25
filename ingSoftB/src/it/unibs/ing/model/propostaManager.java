package it.unibs.ing.model;


import java.util.ArrayList;

public class PropostaManager {

    ArrayList<Proposta> listaProposte;

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

    private void chiudiProposte(ArrayList<Proposta> percorso) {
	    for (Proposta proposta : percorso) {
	        proposta.chiudiProposta();
	    }
	}
	
	public ArrayList<Proposta> getListaProposteUser(String user) {
		ArrayList<Proposta> lista = new ArrayList<>();
		for(Proposta p : listaProposte) {
			if(p.getUsername().equals(user))
				lista.add(p);
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



 
    public boolean verificaProposta(Proposta proposta, ArrayList<Proposta> proposteAperteFromComprensorio) {
 	    
	    for (Proposta p : proposteAperteFromComprensorio) {
	        if (proposta.soddisfaRichiestaDi(p)) {
	            if (proposta.richiestaSoddisfattaDa(p)) {
	                proposta.chiudiProposta();
	                p.chiudiProposta();
	                return true; 
	            } else {	              
	                ArrayList<Proposta> percorso = new ArrayList<>();
	                percorso.add(proposta);

	                if (verificaTransitivaCiclo(proposta, p, percorso, proposteAperteFromComprensorio)) {
	                    chiudiProposte(percorso);
	                    proposta.chiudiProposta();
	                    return true; 
	                }
	            }
	        }
	    }
	    return false;
	}

	
	private boolean verificaTransitivaCiclo(Proposta propostaDaChiudere, Proposta propostaAperta, ArrayList<Proposta> catenaProposte, ArrayList<Proposta> elencoProposte) {
	    catenaProposte.add(propostaAperta);

	    if (catenaProposte.size() > 2 && propostaAperta.soddisfaRichiestaDi(propostaDaChiudere)) {
	        return true;
	        }

	    for (Proposta prossimaProposta : elencoProposte) {
	        if (!catenaProposte.contains(prossimaProposta) && propostaAperta.soddisfaRichiestaDi(prossimaProposta)) {
	            if (verificaTransitivaCiclo(propostaDaChiudere, prossimaProposta, catenaProposte, elencoProposte)) {
	                return true;
	            }
	        }
	    }
	    catenaProposte.remove(catenaProposte.size() - 1);
	    return false;
	}

}
	