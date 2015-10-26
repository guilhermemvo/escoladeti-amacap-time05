package br.unicesumar.time5.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_item_acesso_usuario")
public class ItemAcessoUsuario extends SuperEntidade {

//    @ManyToOne
//    @Column(name = "id_usuario")
//    private Usuario usuario;
//
//    @ManyToOne
//    @Column(name = "id_item_acesso")
//    private ItemAcesso itemAcesso;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "inicio_vigencia")
    private Date inicioVigencia;

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "fim_vigencia")
    private Date fimVigencia;

//    public Usuario getUsuario() {
//        return usuario;
//    }
//
//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }
//
//    public ItemAcesso getItemAcesso() {
//        return itemAcesso;
//    }
//
//    public void setItemAcesso(ItemAcesso itemAcesso) {
//        this.itemAcesso = itemAcesso;
//    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(Date fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

}
