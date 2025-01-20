package it.unibs.ing.view;

import java.util.ArrayList;

import it.unibs.ing.controller.ControllerFruitore;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.Configuratore;
import it.unibs.ing.model.Fruitore;
import java.util.Objects;

public class ViewFruitore {
    ControllerFruitore controllerFruitore;

    public ViewFruitore(ControllerFruitore controllerFruitore) {
        super();
        this.controllerFruitore = controllerFruitore;
    }


    public void start(){

        int scelta = -1;
        do {
            System.out.println("1. Primo accesso fruitore");	
            System.out.println("2. Autenticazione fruitore");
            System.out.println("0. Esci dal programma");
            scelta = InputDati.leggiInteroNonNegativo("Scelta: ");

            switch (scelta) {
                case 1:
                    if(!Objects.isNull(sceltaComprensorio()))
                        registraFruitore(sceltaComprensorio());
                    break;
                case 2:
                    /* Fruitore userLogged = autenticaFruitore();
                    if(userLogged != null){
                        loggedAsFruitore = true;
                        mostraMenuPrincipaleFruitore(userLogged.getUsername());
                    }	 */
                    break;
                case 0: 
                    //salvaDati();
                    System.out.println("Arrivederci!\n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        } while (scelta != 0);
    }


    private ComprensorioGeografico sceltaComprensorio() {
        ComprensorioGeografico comprensorio = null;
        try {
            ArrayList<ComprensorioGeografico> listaComprensori = controllerFruitore.getListaComprensori();    		
	        comprensorio = InputDati.selezionaDaLista(listaComprensori, "Seleziona comprensorio: ");
	    } catch (Exception e) { 
    		System.out.println("Nessun comprensorio disponibile.\n"); 
        }
        return comprensorio;	
    }

    public void registraFruitore(ComprensorioGeografico comprensorio) {
        Boolean userValido = false;
		    while (!userValido) {
		        String username = InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
		        String password = InputDati.leggiStringaNonVuota("Inserisci nuova password: ");
		        String mail = InputDati.leggiStringaNonVuota("Inserisci mail: ");
		
		        userValido = controllerFruitore.userOk(username);
		        if (!userValido) {
		            System.out.println("Username già esistente. Riprova con un altro." + "\n");
		        } else {
		            controllerFruitore.registraFruitore(username, password, comprensorio, mail); 
		         } 
		     
    	
    		}
    }

}
