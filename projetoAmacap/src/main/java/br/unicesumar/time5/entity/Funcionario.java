package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_funcionario")
public class Funcionario extends SuperEntidade{

    @OneToOne
    @JoinColumn(name="id_pessoa_fisica", unique=true, nullable=false, updatable=false)
    private PessoaFisica pessoaFisica;
    
    private String funcao;

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
}
