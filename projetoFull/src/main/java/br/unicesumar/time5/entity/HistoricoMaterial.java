package br.unicesumar.time5.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "tbcad_historico_material")
public class HistoricoMaterial extends SuperEntidade {
    
    @IndexColumn(name = "id")
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataAlteracao;
    private String statusAnterior;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        dataAlteracao.setDate(dataAlteracao.getDate() + 1);
        this.dataAlteracao = dataAlteracao;
    }

    public String getStatusAnterior() {
        return statusAnterior;
    }

    public void setStatusAnterior(String statusAnterior) {
        this.statusAnterior = statusAnterior;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

 
}
