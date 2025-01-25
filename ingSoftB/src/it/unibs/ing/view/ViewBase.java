package it.unibs.ing.view;

import java.io.IOException;
import java.util.List;

import it.unibs.ing.controller.ControllerBase;
import it.unibs.ing.model.*;

public class ViewBase {

    ControllerBase controllerBase;

    public ViewBase(ControllerBase controllerBase) {
        this.controllerBase = controllerBase;
    }

    protected void salvaDati() {
        try {
            controllerBase.salvaDati();
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio dei dati: " + e.getMessage());
        }
    }

    public String toStringDati(Dati dati) {
        return "Dati{" +
                "configuratoreManager=" + visualizzaConfiguratori(dati.getConfiguratoreManager())+
                ", fruitoreManager=" + visualizzaFruitori(dati.getFruitoreManager()) +
                ", comprensorioManager=" + visualizzaComprensori(dati.getComprensorioManager()) +
                '}';
    }

    public String toStringConfig(Configuratore config){
        return "Configuratore{" +
                "username='" + config.getUsername()+ '}';
    }

    public String toStringFruitore(Fruitore fruitore){
        return "Fruitore{" +
                "username='" + fruitore.getUsername() + '}';
    }

    public String toStringComprensorio(ComprensorioGeografico comprensorio){
        return "ComprensorioGeografico{" +
                "nome='" + comprensorio.getNome()+"', "+
                "comuni=" + comprensorio.getComuni().toString()
                + '}';
    }

    public String visualizzaConfiguratori(ConfiguratoreManager listaConfiguratori){
        StringBuilder sb = new StringBuilder();
        for (Configuratore configuratore : listaConfiguratori.getLista()) {
            sb.append(toStringConfig(configuratore)).append("\n");
        }
        return sb.toString();
    }

    public  String visualizzaFruitori(FruitoreManager listaFruitori){
        StringBuilder sb = new StringBuilder();
        for (Fruitore fruitore : listaFruitori.getLista()) {
            sb.append(toStringFruitore(fruitore)).append("\n");
        }
        return sb.toString();  
    } 

    public  String visualizzaComprensori(ComprensorioManager listaComprensori){
        StringBuilder sb = new StringBuilder();
        for (ComprensorioGeografico comprensorio : listaComprensori.getLista()) {
            sb.append(toStringComprensorio(comprensorio)).append("\n");
        }
        return sb.toString();
       }
    
       
    public <T> T selezionaDaLista(List<T> lista, String messaggio) {
        System.out.println(messaggio);

        for (int i = 0; i < lista.size(); i++) {
            T elemento = lista.get(i);
            String descrizione="";

            if (elemento instanceof Fruitore) {
                descrizione = toStringFruitore((Fruitore) elemento);
            } else if (elemento instanceof ComprensorioGeografico) {
                descrizione = toStringComprensorio((ComprensorioGeografico) elemento);
            } else if (elemento instanceof Configuratore) {
                descrizione = toStringConfig((Configuratore) elemento);         
            }
            System.out.println((i + 1) + ". " + descrizione);
        }
        int scelta = InputDati.leggiIntero("Scelta:", 1, lista.size());
        return lista.get(scelta - 1);
    }

    public void nomeNonValido() {
        System.out.println("Nome non valido. Riprovare");
    }

    public void logErroreGenerico(){
        System.out.println("Input non valido. Riprovare");
    }
  
    public void logErroreGerarchia(){
        System.out.println("Non esiste alcuna gerarchia da visualizzare.\n" + //
                        "");
    }

    public void mostraMessaggio(String messaggio){
        System.out.println(messaggio);
    }


}