
package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

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
