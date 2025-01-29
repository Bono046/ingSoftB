package it.unibs.ing.model;

import java.util.ArrayList;

public class ConcreteStrategyProposte implements ChiusuraProposteStrategy {

    public boolean execute(Proposta proposta, ArrayList<Proposta> proposteAperteFromComprensorio) {
 	    
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
    
    private void chiudiProposte(ArrayList<Proposta> percorso) {
	    for (Proposta proposta : percorso) {
	        proposta.chiudiProposta();
	    }
	}
}
