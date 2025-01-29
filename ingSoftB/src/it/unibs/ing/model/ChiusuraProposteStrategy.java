package it.unibs.ing.model;

import java.util.ArrayList;

public interface ChiusuraProposteStrategy {

    boolean execute(Proposta p, ArrayList<Proposta> listaProposte);
    
}
