package it.unibs.ing.model.gerarchia;

import java.util.ArrayList;
import java.util.Stack;

public interface IGerarchia {

    ICategoria getCategoriaRadice();

    ArrayList<ICategoria> getListaFoglie();

    void setListaFoglie(ArrayList<ICategoria> c);

    void addToListaFoglie(ICategoria c);

    ICategoria getCategoriaCorrente();

    void setCategoriaCorrente();

    boolean vaiASottocategoria(ICategoria sottocategoria);

    Stack<ICategoria> getPercorso();

    boolean tornaIndietro();

    ICategoria getFogliaDaNome(String nome);

}