package it.unibs.ing.view;

import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.model.ComprensorioGeografico;
import it.unibs.ing.model.ComprensorioManager;

public class ViewConfiguratore {
    ControllerConfiguratore controllerConfiguratore;

    public ViewConfiguratore(ControllerConfiguratore controllerConfiguratore) {
        super();
        this.controllerConfiguratore = controllerConfiguratore;
    }

    public void start(){

        int scelta = -1;
        do {
            System.out.println("Menu Principale:");
            System.out.println("1. Primo accesso Configuratore");
            System.out.println("2. Autenticazione Configuratore");
            System.out.println("0. Esci dal programma");
            scelta = InputDati.leggiInteroNonNegativo("Scelta: ");

            switch (scelta) {
                case 1:
                    verificaCredenziali();
                    registraConfiguratore();
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
            System.out.println("1. Crea Comprensorio Geografico");
            System.out.println("2. Crea Gerarchia di Categorie");
            System.out.println("3. Stabilisci Fattore di Conversione");
            System.out.println("4. Visualizza Comprensori");
            System.out.println("5. Visualizza Gerarchie");
            System.out.println("6. Visualizza Fattori di Conversione");
            System.out.println("7. Visualizza proposte relative ad una categoria");
            System.out.println("0. Esci");
            int scelta = InputDati.leggiInteroNonNegativo("Seleziona un opzione: ");

            switch (scelta) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 0:

                    System.out.println("Arrivederci! \n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        }
}
