package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_tipo_telefone")
public class TipoTelefone extends SuperEntidade {

    @OneToMany(mappedBy = "tipoTelefone")
    @Column(name = "id_telefone")
    private List<Telefone> telefone;

    private String descricao;

    public TipoTelefone() {
        telefone = new ArrayList<>();
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefones) {
        this.telefone = telefones;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
