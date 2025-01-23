package it.unibs.ing.view;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import it.unibs.ing.controller.ControllerConfiguratore;
import it.unibs.ing.model.GerarchiaCategorie;
import it.unibs.ing.model.Categoria;
import it.unibs.ing.model.CategoriaFoglia;


public class ViewConfiguratore extends ViewBase{
    ControllerConfiguratore controllerConfiguratore;
    ViewBase viewBase = new ViewBase(controllerConfiguratore);

    public ViewConfiguratore(ControllerConfiguratore controllerConfiguratore) {
        super(controllerConfiguratore);
        this.controllerConfiguratore = controllerConfiguratore;
        this.controllerConfiguratore.registraView(this);
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

        System.out.println(toStringDati(controllerConfiguratore.getDati()));

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
                    Categoria root = creaCategoria(true, null);
                	GerarchiaCategorie g = new GerarchiaCategorie(root);
                    creaGerarchia(g, g.getCategoriaRadice());
                    controllerConfiguratore.addGerarchia(g);
                    salvaDati();
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
        System.out.println(visualizzaComprensori(controllerConfiguratore.getDati().getComprensorioManager()));
    }



    private Categoria creaCategoria(boolean isRadice, Categoria radice) {
    	
    	String nome = "";
    	
    	if(isRadice) {	
            nome = getNomeRadice();
    	}else 
    		nome = getNomeCategoriaValido(radice);
    	
        String campo = InputDati.leggiStringaNonVuota("Inserisci nome del campo: ");
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
        Categoria c = controllerConfiguratore.creaCategoria(nome, campo, dominio);
        return c;
    }


    private String getNomeRadice() {
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

    private String getNomeCategoriaValido(Categoria radice) {		
    	String nome = "";
    	Boolean nomeValido=false;
    
    	while(!nomeValido) {
    		nome = InputDati.leggiStringaNonVuota("Inserisci nome categoria: ");
    		nomeValido = radice.isNomeUnivoco(nome);
    		if(!nomeValido) 
    			System.out.println("Nome non valido. Riprovare");
    	}
    	return nome;
    }

    private CategoriaFoglia creaCategoriaFoglia(Categoria radice) {
        String nome = getNomeCategoriaValido(radice); 		
    
        CategoriaFoglia categoria = new CategoriaFoglia(nome);
        System.out.println("Categoria foglia creata con successo." + "\n");
        return categoria;
    }


   private void creaGerarchia(GerarchiaCategorie g, Categoria padre) {			
    	System.out.println("categoria selezionata: " + padre.getNome());
    	
    	ArrayList<String> listaDominio = new ArrayList<>(padre.getDominio().keySet());
        Categoria radice = g.getCategoriaRadice();
    	
    	for (String dom : listaDominio) {
            System.out.println("Creazione sottocategoria per il dominio: " + dom);
            Boolean input=false;
            while(!input) {
		    	System.out.println(" 1 - aggiungi sottocategoria, 2 - aggiungi categoria foglia");
		    	int choice1 = InputDati.leggiIntero(dom, 1, 2);
		    	
		    	switch(choice1) {
		    		case 1:
			    		Categoria sottocat = creaCategoria(false, radice);
			            padre.aggiungiSottocategoria(dom, sottocat);
			    		HashMap<String, Categoria> sottocategorie = padre.getSottocategorie();
			    		creaGerarchia(g, sottocategorie.get(dom));
			    		input = true;
			    		break;
		    		case 2:
			    		CategoriaFoglia sottocatF = creaCategoriaFoglia(radice);
			            padre.aggiungiSottocategoria(dom, sottocatF);
			            g.addToListaFoglie(sottocatF);
			            input = true;
			            break;
			        default:
			        	System.out.println("input non valido. Riprovare");
		    	}
            }
        }
    }



}