package it.unibs.ing.model.fattore;

import java.util.ArrayList;
import it.unibs.ing.model.IManager;

public interface IFattoreManager extends IManager<IFattore> {

    void addFattore(String c1, String c3, double f13);
    Boolean esisteFattore(String c1, String c2);
    ArrayList<IFattore> trovaFattore(String s);
    IFattore trovaFattore(String foglia1, String foglia2);

}