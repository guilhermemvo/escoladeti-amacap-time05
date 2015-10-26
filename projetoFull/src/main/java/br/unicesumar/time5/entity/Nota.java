package br.unicesumar.time5.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_nota")
public class Nota extends SuperEntidade {

    @Column(name = "id_pedido")
    private Long pedido;

    private String observacao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_nota;

    public Long getPedido() {
        return pedido;
    }

    public void setPedido(Long pedido) {
        this.pedido = pedido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getData_nota() {
        return data_nota;
    }

    public void setData_nota(Date data_nota) {
        this.data_nota = data_nota;
    }

}
