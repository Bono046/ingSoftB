package it.unibs.ing.model;

import java.util.ArrayList;

public interface IManager<T> {
    ArrayList<T> getLista();
    void addElemento(T element);
}
