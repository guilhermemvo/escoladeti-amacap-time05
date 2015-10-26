package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_telefone")
public class Telefone extends SuperEntidade {

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoas;

    @ManyToOne
    @JoinColumn(name = "id_tipo_telefone")
    private TipoTelefone tipoTelefone;
    private String numero;
    
    public TipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(TipoTelefone tipotelefone) {
        this.tipoTelefone = tipotelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Pessoa getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoa pessoas) {
        this.pessoas = pessoas;
    }

}
