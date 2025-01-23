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

    /*
    public void setNome(String nome) {
        this.nome = nome;
    }
*/

    public Set<String> getComuni() {
        return comuni;
    }

    /*
    public void setComuni(Set<String> comuni) {
        this.comuni = comuni;
    }

    public void aggiungiComune(String comune) {
        comuni.add(comune);
    }

    public void rimuoviComune(String comune) {
        comuni.remove(comune);
    }

    public boolean contieneComune(String comune) {
        return comuni.contains(comune);
    }
*/
 


} 