package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_professor")
public class Professor extends SuperEntidade{

    @OneToOne
    @JoinColumn(name="id_pessoa_fisica", unique=true, nullable=false, updatable=false)
    private PessoaFisica pessoaFisica;
    
    @ManyToOne
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;
    
    @ManyToOne
    @JoinColumn(name = "id_escola")
    private Escola escola;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }
}
