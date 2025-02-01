package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;
import it.unibs.ing.model.IManager;

public interface IGerarchiaManager extends IManager<IGerarchia> {

    ArrayList<ICategoria> getListaRadici();
    boolean checkNomeGerarchia(String nome);

}