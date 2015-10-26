
package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author johnLima
 */

@Entity
@Table(name = "tbcad_materia_prima")
public class MateriaPrima extends SuperEntidade {
    
    @Column(name="nome")
    private String nome;
    
    @Column(name="quantidade")
    private int quantidade;
    
    @Column(name="valor_unitario")
    private float valorUnitario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public float getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(float valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
}
