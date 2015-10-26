package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_editoras")
public class Editoras extends SuperEntidade{

    @OneToOne
    @JoinColumn(name="id_pessoa_juridica", unique=true, nullable=false, updatable=false)
    private PessoaJuridica pessoaJuridica;

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }
}
