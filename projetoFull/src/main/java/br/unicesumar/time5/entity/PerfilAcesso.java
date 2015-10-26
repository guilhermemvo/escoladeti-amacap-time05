package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_perfil_acesso")
public class PerfilAcesso extends SuperEntidade {

    private String descricao;
    
     /*
    * Mapeamento ManyToMany sem campos adicionais entre PerfilAcesso e ItemAcesso
  
    @ManyToMany
    @JoinTable(name="tbcad_perfil_acesso_item_acesso", joinColumns={@JoinColumn(name="id_perfil_acesso")}, inverseJoinColumns={@JoinColumn(name="id_item_acesso")})
    private List<ItemAcesso>itemAcesso;
  */
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
