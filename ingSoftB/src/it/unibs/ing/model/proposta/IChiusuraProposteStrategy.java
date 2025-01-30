package it.unibs.ing.model.proposta;

import java.util.ArrayList;

public interface IChiusuraProposteStrategy {

    boolean execute(IProposta p, ArrayList<IProposta> listaProposte);
    
}
