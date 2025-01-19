package it.unibs.ing.view;

import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.controller.ControllerFruitore;

public class ViewInit {

    ControllerConfiguratore controllerConfiguratore;
    ControllerFruitore controllerFruitore;;

    public ViewInit(ControllerConfiguratore controllerConfiguratore, ControllerFruitore controllerFruitore) {
        this.controllerConfiguratore = controllerConfiguratore;
        this.controllerFruitore = controllerFruitore;
    }

    public void start() {
        int scelta = -1;
        do {
            System.out.println("Menu Principale:");
            System.out.println("1. Accesso configuratore");
            System.out.println("2. Accesso fruitore");
            System.out.println("0. Esci dal programma");
            scelta = InputDati.leggiInteroNonNegativo("Scelta: ");

            switch (scelta) {
                case 1:
                    ViewConfiguratore viewConfig = new ViewConfiguratore(controllerConfiguratore);
                    viewConfig.start();
                    break;
                case 2:
                    ViewFruitore viewFruitore = new ViewFruitore(controllerFruitore);
                    viewFruitore.start();
                    break;
                case 0:
                    //salvaDati();
                    System.out.println("Arrivederci!\n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        }
        while (scelta != 0);
    }
}


