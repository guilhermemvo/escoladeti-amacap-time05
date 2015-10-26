package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_item_acesso")
public class ItemAcesso extends SuperEntidade {

    @Column(name = "titulo_tela")
    private String tituloTela;

//    @OneToOne
//    @Column(name = "id_perfil_acesso")
//    private PerfilAcesso perfilAcesso;

    public String getTituloTela() {
        return tituloTela;
    }

    public void setTituloTela(String tituloTela) {
        this.tituloTela = tituloTela;
    }

//    public PerfilAcesso getPerfilAcesso() {
//        return perfilAcesso;
//    }
//
//    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
//        this.perfilAcesso = perfilAcesso;
//    }

}
