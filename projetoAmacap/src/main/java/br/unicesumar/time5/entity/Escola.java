package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_escola")
public class Escola extends SuperEntidade {

    @OneToOne
    @JoinColumn(name="id_pessoa_juridica", unique=true, nullable=false, updatable=false)
    private PessoaJuridica pessoaJuridica;

    @OneToMany(mappedBy = "escola")
    @Column(name = "id_professor")
    private List<Professor> professores;

    @OneToMany(mappedBy = "escola")
    @Column(name = "id_aluno")
    private List<Aluno> aluno;

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public List<Aluno> getAluno() {
        return aluno;
    }

    public void setAluno(List<Aluno> aluno) {
        this.aluno = aluno;
    }
}
