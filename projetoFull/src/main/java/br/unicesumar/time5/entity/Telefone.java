package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_telefone")
public class Telefone extends SuperEntidade {

    private String numero;

    @ManyToOne
    @JoinColumn(name = "id_tipo_telefone")
    private TipoTelefone tipotelefone;

    @ManyToOne
    @JsonIgnore
    private Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipotelefone() {
        return tipotelefone;
    }

    public void setTipotelefone(TipoTelefone tipotelefone) {
        this.tipotelefone = tipotelefone;
    }

}
