package it.unibs.ing.controller;

import java.io.IOException;

import it.unibs.ing.view.ViewInit;
import it.unibs.ing.model.Dati;
import it.unibs.ing.model.FileManager;


public class Main {
	public static void main(String[] args) {

        Dati dati = new Dati();
        
         try {
            dati = FileManager.caricaDati();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dei dati: " + e.getMessage());
            dati = new Dati();
        } 
        
        ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(dati);
        ControllerFruitore controllerFruitore = new ControllerFruitore(dati);
        ViewInit viewInit = new ViewInit(controllerConfiguratore, controllerFruitore);
        viewInit.start();
    }

}
