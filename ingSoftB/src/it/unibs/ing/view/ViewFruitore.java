package it.unibs.ing.view;

import java.util.ArrayList;
import java.util.Map;

import it.unibs.ing.controller.ControllerFruitore;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.gerarchia.ICategoria;


public class ViewFruitore extends ViewBase {

    ControllerFruitore controllerFruitore;

    public ViewFruitore(ControllerFruitore controllerFruitore) {
        super(controllerFruitore);
        this.controllerFruitore = controllerFruitore;
        controllerFruitore.registraView(this);
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
                IComprensorio comprensorio = sceltaComprensorio();
                    if(comprensorio != null)
                        registraFruitore(comprensorio);
                        salvaDati();
                    break;
                case 2:
                    String user = autenticaFruitore();
                    if(!user.isEmpty())
                        mostraMenuPrincipaleFruitore(user);
                    break;
                case 0: 
                
                    System.out.println("Arrivederci!\n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
           
        } while (scelta != 0);
    }


    private IComprensorio sceltaComprensorio() {
        IComprensorio comprensorio = null;
        try {
            ArrayList<IComprensorio> listaComprensori = controllerFruitore.getListaComprensori();    		
	        comprensorio = selezionaDaLista(listaComprensori, "Seleziona comprensorio: ");
	    } catch (Exception e) { 
    		System.out.println("Nessun comprensorio disponibile.\n"); 
        }
        return comprensorio;	
    }

    public void registraFruitore(IComprensorio comprensorio) {
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

    private String autenticaFruitore() {
        String username = InputDati.leggiStringaNonVuota("Inserisci username: ");
        String password = InputDati.leggiStringaNonVuota("Inserisci password: ");

        Boolean auth = controllerFruitore.loginFruitore(username, password);
        if (auth){
            System.out.println("Autenticazione avvenuta con successo. Procedi con il seguente menu:");
            return username;
        }else {
            System.out.println("Credenziali non valide. Riprova" + "\n");
            return "";
        }
    }

    

    private void mostraMenuPrincipaleFruitore(String user) {
    	while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Esplora gerarchie");
            System.out.println("2. Formula proposta scambio");
            System.out.println("3. Visualizza proposte di scambio");
            System.out.println("4. Ritira proposta di scambio");
            System.out.println("0. Esci");
            System.out.print("Seleziona un'opzione: ");

            int scelta = InputDati.leggiInteroNonNegativo("Scelta: ");

            switch (scelta) {
                case 1:
                	controllerFruitore.esploraGerarchia();
                    break;
                case 2: 
                	controllerFruitore.creaProposta(user);
                    salvaDati();
                	break;
                case 3:
                    controllerFruitore.visualizzaProposteByUser(user);
                	break;
                case 4:
                	controllerFruitore.ritiraProposta(user);
                    salvaDati();
                	break;
                case 0:
                    
                    System.out.println("Arrivederci!\n");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova" + "\n");
            }
        }
    }


    public void mostraCategoriaCorrente(String nomeCategoria) {
        System.out.println("\nCategoria corrente: " + nomeCategoria);
    }

    public void mostraValoriSottocategorie(Map<String, ICategoria> sottocategorie) {
        System.out.println("Valori disponibili:");
        sottocategorie.forEach((k, v) -> System.out.println(k + " -> " + v.getNome()));
    }

    

 

    
   



}
