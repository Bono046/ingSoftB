package it.unibs.ing.view;

import java.io.IOException;

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
                "configuratoreManager=" + toStringConfigManager(dati.getConfiguratoreManager())+
                ", fruitoreManager=" + tostringFruitoreManager(dati.getFruitoreManager()) +
                ", comprensorioManager=" + toStringComprensorioManager(dati.getComprensorioManager()) +
                '}';
    }

    public String toStringConfig(Configuratore config){
        return "Configuratore{" +
                "username='" + config.getUsername();
    }

    public String toStringFruitore(Fruitore fruitore){
        return "Fruitore{" +
                "username='" + fruitore.getUsername();
    }

    public String toStringComprensorio(ComprensorioGeografico comprensorio){
        return "ComprensorioGeografico{" +
                "nome='" + comprensorio.getNome()+
                "comuni=" + comprensorio.getComuni();
    }

    public String toStringComprensorioManager(ComprensorioManager comprensorioManager){
        return "ComprensorioManager{" +
                "listaComprensori=" + comprensorioManager.getListaComprensori();
    }

    public String toStringConfigManager(ConfiguratoreManager configuratoreManager){
        return "ConfiguratoreManager{" +
                "listaConfiguratori=" + configuratoreManager.getListaConfiguratori();
    }

    public String tostringFruitoreManager(FruitoreManager fruitoreManager){
        return "FruitoreManager{" +
                "listaFruitori=" + fruitoreManager.getListaFruitori().toString();
    }
    





}