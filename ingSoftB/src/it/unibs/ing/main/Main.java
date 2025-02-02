package it.unibs.ing.main;

import java.io.IOException;

import com.google.gson.JsonParseException;

import it.unibs.ing.view.ViewInit;
import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.controller.ControllerFruitore;
import it.unibs.ing.database.FileManager;
import it.unibs.ing.model.Dati;


public class Main {
	public static void main(String[] args) {

        Dati dati;
        
        try {
            dati = FileManager.caricaDati();
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dei dati: " + e.getMessage());
            dati = Dati.getInstance();
        } catch (IllegalStateException e ) {
            System.out.println(e.getMessage()+": verificare la correttezza del file json o procedere per sovrascrivere i dati");
            dati = Dati.getInstance();
        } catch(JsonParseException e){
            System.out.println(e.getMessage()+": verificare la correttezza del file json o procedere per sovrascrivere i dati");
            dati = Dati.getInstance();
        } 
        
        ControllerConfiguratore controllerConfiguratore = new ControllerConfiguratore(dati);
        ControllerFruitore controllerFruitore = new ControllerFruitore(dati);
        ViewInit viewInit = new ViewInit(controllerConfiguratore, controllerFruitore);
        viewInit.start();
    }

}
