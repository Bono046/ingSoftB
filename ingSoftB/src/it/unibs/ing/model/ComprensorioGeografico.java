package it.unibs.ing.model;


import java.util.Set;

public class ComprensorioGeografico {

    private String nome;
    private Set<String> comuni;

    public ComprensorioGeografico(String nome, Set<String> comuni) {
        this.nome = nome;
        this.comuni = comuni;
    }

    public String getNome() {
        return nome;
    }


    public Set<String> getComuni() {
        return comuni;
    }


} 