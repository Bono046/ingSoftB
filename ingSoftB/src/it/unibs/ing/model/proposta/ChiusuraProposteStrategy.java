package it.unibs.ing.model.proposta;

import java.util.ArrayList;

public interface ChiusuraProposteStrategy {

    boolean execute(Proposta p, ArrayList<Proposta> listaProposte);
    
}
