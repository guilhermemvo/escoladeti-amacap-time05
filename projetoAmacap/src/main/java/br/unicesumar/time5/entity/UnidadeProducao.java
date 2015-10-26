/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author johnLima
 */
@Entity
@Table(name = "tbcad_unidade_producao")
public class UnidadeProducao extends SuperEntidade {

    @IndexColumn(name = "id")

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
