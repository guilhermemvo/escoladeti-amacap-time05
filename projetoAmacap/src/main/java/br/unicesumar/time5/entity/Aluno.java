
package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_aluno")
public class Aluno extends SuperEntidade{
    
    @OneToOne
    @JoinColumn(name="id_pessoa_fisica", unique=true, nullable=false, updatable=false)
    private PessoaFisica pessoaFisica;
    
    private String deficiencia;
    private String serie;
    private String turma;
    
    @OneToMany(mappedBy = "aluno")
    @Column(name = "id_professor")
    private List<Professor> professores;
    
    @ManyToOne
    @JoinColumn(name = "id_escola")
    private Escola escola;

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
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
