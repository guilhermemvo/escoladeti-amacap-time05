package br.unicesumar.escoladeti.entity;

import java.util.Objects;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

/**
 *
 * @author RodrigoCezar
 */
@Entity
public class Endereco extends SuperEntidade implements Comparable<Endereco> {

    @NotNull
    private String cep;
    @NotNull
    private String numero;
    private String complemento;

//    private Bairro bairro;
//    private Logradouro logradouro;
    public Endereco() {
    }

    public Endereco(Long id) {
        this.id = id;
    }

    @Override
    public int compareTo(Endereco outroEndereco) {
        if (outroEndereco == null) {
            return -1;
        }

        if (getCep() == null && outroEndereco.getCep() == null) {
            return 0;
        }

        if (outroEndereco.getCep() == null) {
            return -1;
        }

        if (getCep() == null) {
            return 1;
        }

        return getCep().compareTo(outroEndereco.getCep());
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
