package br.unicesumar.time5.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_usuario_perfil_acesso")
public class UsuarioPerfilAcesso extends SuperEntidade {

//    @ManyToOne
//    @Column(name = "id_usuario")
//    private Usuario usuario;
//    @ManyToOne
//    @Column(name = "id_perfil_acesso")
//    private PerfilAcesso perfilAcesso;

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
//    public PerfilAcesso getPerfilAcesso() {
//        return perfilAcesso;
//    }
//
//    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
//        this.perfilAcesso = perfilAcesso;
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
