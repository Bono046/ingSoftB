package it.unibs.ing.model.proposta;

import java.util.ArrayList;
import it.unibs.ing.model.IManager;

public interface IPropostaManager extends IManager<IProposta> {

    void setChiusuraProposteStrategy(IChiusuraProposteStrategy chiusuraProposteStrategy);
    boolean verificaProposta(IProposta proposta, ArrayList<IProposta> listaProposte);
    ArrayList<IProposta> getListaProposteUser(String user);
    ArrayList<IProposta> getListaProposteAperteUser(String user);
    ArrayList<IProposta> getProposteAperteFromUsers(ArrayList<String> listaUser);

}