package it.unibs.ing.view;


import java.util.ArrayList;
import java.util.List;
import it.unibs.ing.controller.ControllerBase;
import it.unibs.ing.model.*;
import it.unibs.ing.model.comprensorio.IComprensorio;
import it.unibs.ing.model.fattore.FattoreConversione;
import it.unibs.ing.model.gerarchia.CategoriaFoglia;
import it.unibs.ing.model.gerarchia.ICategoria;
import it.unibs.ing.model.proposta.Proposta;

public class ViewBase {

    ControllerBase controllerBase;

    public ViewBase(ControllerBase controllerBase) {
        this.controllerBase = controllerBase;
    }

    protected void salvaDati() {
        try {
            controllerBase.salvaDati();
        } catch (IllegalStateException e) {
            System.out.println("Attenzione: errore nel salvataggio dei dati\n" +
             "File ripristinato e salvataggio eseguito con successo");
        }
    }


    public String toStringComprensorio(IComprensorio comprensorio){
        return "ComprensorioGeografico{" +
                "nome='" + comprensorio.getNome()+"', "+
                "comuni=" + comprensorio.getComuni().toString()
                + '}';
    }

    public String toStringFattore(FattoreConversione fattore){
        return "FattoreConversione{Richiesta:  " + fattore.getC1() + ", Offerta " + fattore.getC2() + 
        ", fattore " + fattore.getFattore() + '}';
    }

    public String toStringCategoriaFoglia(ICategoria categoria){
        return "CategoriaFoglia{" +
                "nome='" + categoria.getNome() + '}';
    }

    public String toStringProposta(Proposta p){
        return "Proposta{" +"richiesta: " + p.getRichiesta() +", durata: " + p.getDurataRichiesta()
				+ "; offerta: " + p.getOfferta() + ", durata: " + p.getDurataOfferta() + ", stato: " + p.getStato();    
    }

    public boolean chiediConferma(String messaggio) {
        return InputDati.yesOrNo(messaggio);
    }

    public void visualizzaComprensori(ArrayList<IComprensorio> listaComprensori){
        StringBuilder sb = new StringBuilder();
        for (IComprensorio comprensorio : listaComprensori) {
            sb.append(toStringComprensorio(comprensorio)).append("\n");
        }
        System.out.println(sb.toString());
    }

  
    public void visualizzaProposte(ArrayList<Proposta> listaProposte){
        StringBuilder sb = new StringBuilder();
        for (Proposta proposta : listaProposte) {
            sb.append(toStringProposta(proposta)).append("\n");
        }
       System.out.println(sb.toString());
    }
    
       
    public <T> T selezionaDaLista(List<T> lista, String messaggio) {
        System.out.println(messaggio);

        for (int i = 0; i < lista.size(); i++) {
            T elemento = lista.get(i);
            String descrizione="";

            if (elemento instanceof IComprensorio) {
                descrizione = toStringComprensorio((IComprensorio) elemento);
            } else if (elemento instanceof CategoriaFoglia) {
                descrizione = toStringCategoriaFoglia((CategoriaFoglia) elemento); 
            }else if (elemento instanceof Proposta) {
                    descrizione = toStringProposta((Proposta) elemento); 
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

     public String leggiValore(String messaggio) {
        return InputDati.leggiStringaNonVuota(messaggio);
    }
    
    public int leggiValoreIntero(String messaggio) {
        return InputDati.leggiIntero(messaggio);
    } 

    public int selezionaGerarchia(List<String> nomiGerarchie) {
        for (int i = 0; i < nomiGerarchie.size(); i++) {
            System.out.println((i + 1) + ". " + nomiGerarchie.get(i));
        }
        int scelta = InputDati.leggiIntero("Scegli una gerarchia: ", 1, nomiGerarchie.size());
        return scelta;
    }

    public String chiediNomeCategorieFoglia(ArrayList<ICategoria> listaFoglie) {
        return selezionaDaLista(listaFoglie, "Seleziona una categoria foglia: ").getNome();
    }


   



}