package it.unibs.ing.model.comprensorio;

import java.util.Set;

public class Comprensorio implements IComprensorio {

    private String nome;
    private Set<String> comuni;

    public Comprensorio(String nome, Set<String> comuni) {
        this.nome = nome;
        this.comuni = comuni;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Set<String> getComuni() {
        return comuni;
    }

} 