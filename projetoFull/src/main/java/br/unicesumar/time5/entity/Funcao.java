package br.unicesumar.time5.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author Paulo Henrique Machado Dias
 */
@Entity
@Table(name = "tbcad_funcao")
public class Funcao extends SuperEntidade implements Serializable {
    
    @IndexColumn(name = "id")

    private String descricao;    
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
