package it.unibs.ing.view;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.ComprensorioManager;
import it.unibs.ing.model.ConfiguratoreManager;

public class ViewConfiguratore extends ViewBase{
    ControllerConfiguratore controllerConfiguratore;
    ViewBase viewBase = new ViewBase(controllerConfiguratore);

    public ViewConfiguratore(ControllerConfiguratore controllerConfiguratore) {
        super(controllerConfiguratore);
        this.controllerConfiguratore = controllerConfiguratore;
    }

    public void start(){
        int scelta = -1;
        do {
            System.out.println(toStringDati(controllerConfiguratore.getDati()));
            System.out.println("Menu Principale:");
            System.out.println("1. Primo accesso Configuratore");
            System.out.println("2. Autenticazione Configuratore");
            System.out.println("0. Esci dal programma");
            scelta = InputDati.leggiInteroNonNegativo("Scelta: ");


            switch (scelta) {
                case 1:
                    verificaCredenziali();
                    registraConfiguratore();
                    salvaDati();
                    break;
                case 2:
                    if (autenticaConfiguratore())
                        mostraMenuPrincipaleConfig();
                    break;
                case 0:
                    System.out.println("Arrivederci!\n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        } while (scelta != 0);
    }


    private void verificaCredenziali() {
        Boolean check = false;
        while (!check) {
            String usernamePredefinito = InputDati.leggiStringaNonVuota("Inserisci username predefinito: ");
            String passwordPredefinita = InputDati.leggiStringaNonVuota("Inserisci password predefinita: ");
            check = controllerConfiguratore.verificaCredenzialiPrimoAccesso(usernamePredefinito, passwordPredefinita);
            if (!check)
                System.out.println("Credenziali predefinite errate. Riprova" + "\n");
        }
    }

    private void registraConfiguratore() {
        Boolean userValido = false;
        while (!userValido) {
            String username = InputDati.leggiStringaNonVuota("Inserisci nuovo username: ");
            String password = InputDati.leggiStringaNonVuota("Inserisci nuova password: ");

            userValido = controllerConfiguratore.userOk(username);
            if (!userValido) {
                System.out.println("Username già esistente. Riprova con un altro." + "\n");
            } else {
                controllerConfiguratore.registraConfiguratore(username, password);
            }
        }
        // salvaDati();   DA SISTEMARE 
    }

    private Boolean autenticaConfiguratore() {
        String username = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String password = InputDati.leggiStringaNonVuota("Inserisci password: ");

        Boolean auth = controllerConfiguratore.loginConfiguratore(username, password);
        if (auth)
            System.out.println("Autenticazione avvenuta con successo. Procedi con il seguente menu:");
        else
            System.out.println("Credenziali non valide. Riprova" + "\n");
        return auth;
    }


    private void mostraMenuPrincipaleConfig() {
        int scelta;
        do {
            System.out.println("1. Crea Comprensorio Geografico");
            System.out.println("2. Crea Gerarchia di Categorie");
            System.out.println("3. Stabilisci Fattore di Conversione");
            System.out.println("4. Visualizza Comprensori");
            System.out.println("5. Visualizza Gerarchie");
            System.out.println("6. Visualizza Fattori di Conversione");
            System.out.println("7. Visualizza proposte relative ad una categoria");
            System.out.println("0. Esci");
            scelta = InputDati.leggiInteroNonNegativo("Seleziona un'opzione: ");

            switch (scelta) {
                case 1:
                    creaComprensorio();
                    salvaDati();
                    break;
                case 2:
                    // Implementa la logica per creare gerarchie di categorie
                    break;
                case 3:
                    // Implementa la logica per stabilire fattori di conversione
                    break;
                case 4:
                    visualizzaComprensori();
                    break;
                case 5:
                    // Implementa la logica per visualizzare gerarchie
                    break;
                case 6:
                    // Implementa la logica per visualizzare fattori di conversione
                    break;
                case 7:
                    // Implementa la logica per visualizzare proposte relative ad una categoria
                    break;
                case 0:
                    System.out.println("Arrivederci! \n");
                    break;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        } while (scelta != 0);
    }

    public void creaComprensorio(){
        String nome = InputDati.leggiStringaNonVuota("Inserisci nome comprensorio: ");
        Set<String> comuni = new HashSet<>();
        String input;
		do {
			input = InputDati.leggiStringaNonVuota("Inserisci nome del comune da aggiungere. Premi 0 per uscire: ");
			if(!input.equals("0"))
				comuni.add(input);
		} while(!input.equals("0"));
        controllerConfiguratore.aggiungiComprensorio(nome, comuni);
    }

    public void visualizzaComprensori(){
        System.out.println(toStringComprensorioManager(controllerConfiguratore.getDati().getComprensorioManager()));
    }

    
}