package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tbcad_bairro")
public class Bairro extends SuperEntidade {

    private String nome;

    @ManyToOne()
    @JoinColumn(name = "id_cidade")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Cidade cidade;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tbcad_logradouro_bairro",
            joinColumns = @JoinColumn(name = "id_bairro", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_logradouro", referencedColumnName = "id"))
    private List<Logradouro> logradouros;

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
