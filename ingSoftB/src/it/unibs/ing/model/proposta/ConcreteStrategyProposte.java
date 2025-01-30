package it.unibs.ing.model.proposta;

import java.util.ArrayList;

public class ConcreteStrategyProposte implements IChiusuraProposteStrategy {

    public boolean execute(IProposta proposta, ArrayList<IProposta> proposteAperteFromComprensorio) {
 	    
	    for (IProposta p : proposteAperteFromComprensorio) {
	        if (proposta.soddisfaRichiestaDi(p)) {
	            if (proposta.richiestaSoddisfattaDa(p)) {
	                proposta.chiudiProposta();
	                p.chiudiProposta();
	                return true; 
	            } else {	              
	                ArrayList<IProposta> percorso = new ArrayList<>();
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

	
	private boolean verificaTransitivaCiclo(IProposta propostaDaChiudere, IProposta propostaAperta, ArrayList<IProposta> catenaProposte, ArrayList<IProposta> elencoProposte) {
	    catenaProposte.add(propostaAperta);

	    if (catenaProposte.size() > 2 && propostaAperta.soddisfaRichiestaDi(propostaDaChiudere)) {
	        return true;
	    }

	    for (IProposta prossimaProposta : elencoProposte) {
	        if (!catenaProposte.contains(prossimaProposta) && propostaAperta.soddisfaRichiestaDi(prossimaProposta)) {
	            if (verificaTransitivaCiclo(propostaDaChiudere, prossimaProposta, catenaProposte, elencoProposte)) {
	                return true;
	            }
	        }
	    }
	    catenaProposte.remove(catenaProposte.size() - 1);
	    return false;
	}
    
    private void chiudiProposte(ArrayList<IProposta> percorso) {
	    for (IProposta proposta : percorso) {
	        proposta.chiudiProposta();
	    }
	}
}
