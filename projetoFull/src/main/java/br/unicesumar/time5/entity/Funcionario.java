package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_funcionario")
public class Funcionario extends PessoaFisica {

    @ManyToOne
    @JoinColumn(name = "id_funcao")
    private Funcao funcao;

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

}
