package it.unibs.ing.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.model.FattoreConversione;
import it.unibs.ing.model.ComponenteCategoria;

public class ViewConfiguratore extends ViewBase{

    ControllerConfiguratore controllerConfiguratore;

    public ViewConfiguratore(ControllerConfiguratore controllerConfiguratore) {
        super(controllerConfiguratore);
        this.controllerConfiguratore = controllerConfiguratore;
        this.controllerConfiguratore.registraView(this);
    }

    public void start() {
        int scelta = -1;
        do {
            
            System.out.println("\nMenu Principale:");
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
            System.out.println("\n MENU");
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
                    controllerConfiguratore.inizializzaGerarchia();
                    salvaDati();
                    break;
                case 3:
                    controllerConfiguratore.setFattoriConversione();
                    salvaDati();
                    break;
                case 4:
                    visualizzaComprensori();
                    break;
                case 5:
                    controllerConfiguratore.visualizzaGerarchie();
                    break;
                case 6:
                    controllerConfiguratore.visualizzaFattoriConversione();
                    break;
                case 7:
                    controllerConfiguratore.visualizzaProposteByFoglia();
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
        System.out.println(visualizzaComprensori(controllerConfiguratore.getDati().getComprensorioManager()));
    }





    public String leggiCampo() {
        return InputDati.leggiStringaNonVuota("Inserisci nome del campo: ");
    }

    public  HashMap<String, String> leggiDominio() {
        HashMap<String, String> dominio = new HashMap<>();
       
        int numValori = InputDati.leggiInteroNonNegativo("Quanti valori di dominio vuoi inserire? ");
  
        for (int i = 0; i < numValori; i++) {
        	String valore;
        	String descrizione;
        	Boolean valido = false;
            do {
	        	valore = InputDati.leggiStringaNonVuota("Inserisci valore del dominio: ");
	            descrizione = InputDati.leggiStringa("Inserisci descrizione (premere invio per lasciarla vuota): ");
	            if(dominio.containsKey(valore))
	            	System.out.println("Valore duplicato. Riprovare");
	            else valido = true;
            }while(!valido);
            
            dominio.put(valore, descrizione);
        }
        return dominio;

    }


    public String getNomeRadice() {
            boolean nomeValido;
            String nome;
    		do {
    			nome= InputDati.leggiStringaNonVuota("Inserisci nome categoria: ");
    			nomeValido=controllerConfiguratore.checkNomeGerarchia(nome);
        			if(!nomeValido) 
        				System.out.println("Nome non valido. Riprovare");
        	} while(!nomeValido);
            return nome;    
    }

    public String getNomeCategoria() {		
    	String nome = InputDati.leggiStringaNonVuota("Inserisci nome categoria: ");
        return nome;
    }

    public int stampaSceltaSottocategoria(String dom){
        System.out.println("Creazione sottocategoria per il dominio: " + dom);
        int input = InputDati.leggiIntero(" 1 - aggiungi sottocategoria, 2 - aggiungi categoria foglia: ", 1, 2);
        return input;
    }


   
    public void stampaAlbero(String indentazione, ComponenteCategoria c) {
        try {
            StringBuilder result = new StringBuilder(indentazione + "- " + c.getNome() + " (" + c.getCampo() + "= [");
            List<String> coppie = new ArrayList<>();
            for (Map.Entry<String, String> dominio : c.getDominio().entrySet()) {
                String chiave = dominio.getKey();
                String valore = dominio.getValue();
                
                if (valore.isEmpty()) {
                    coppie.add(chiave);
                } else {
                    coppie.add(chiave + ": " + valore);
                }
            }
            result.append(String.join(", ", coppie));
            result.append("])");
            System.out.println(result.toString());

        } catch (NullPointerException e) {
            System.out.println(indentazione + "- " + c.getNome());
        }

        if (!c.isFoglia()) {
            for (ComponenteCategoria sottocategoria : c.getSottocategorie().values()) {
                stampaAlbero(indentazione + "  ", sottocategoria);
            }
        }
    }

   


    public double leggiFattore(String messaggio, double min, double max) {    
        double fattore = InputDati.leggiDouble(messaggio, min, max);
        return fattore;
    }



    public void mostraFattoriConversione(ArrayList<FattoreConversione> fattori) {
        for (FattoreConversione f : fattori) {
                System.out.println(toStringFattore(f));
        }
    }
    

    



}