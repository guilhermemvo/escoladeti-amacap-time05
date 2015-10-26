package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_foto")
public class Foto extends SuperEntidade {

    private String descricao;
    private String origem;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

}
