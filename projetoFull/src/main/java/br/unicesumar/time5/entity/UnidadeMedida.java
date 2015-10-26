package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tbcad_unidade_medida")
public class UnidadeMedida extends SuperEntidade{
	
	private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
