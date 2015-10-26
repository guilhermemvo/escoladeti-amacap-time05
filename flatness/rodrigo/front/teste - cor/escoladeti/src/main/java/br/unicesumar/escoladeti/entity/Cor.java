package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;

/**
 *
 * @author RodrigoCezar
 */
@Entity
public class Cor extends SuperEntidade implements Comparable<Cor> {

    private String nome;

    public Cor() {
    }

    public Cor(String nome) {
        this(null, nome);
    }

    public Cor(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int compareTo(Cor outraCor) {
        if (outraCor == null) {
            return -1;
        }

        if (getNome() == null && outraCor.getNome() == null) {
            return 0;
        }

        if (outraCor.getNome() == null) {
            return -1;
        }

        if (getNome() == null) {
            return 1;
        }

        return getNome().compareTo(outraCor.getNome());
    }

}
