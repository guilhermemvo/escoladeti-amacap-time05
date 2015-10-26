package br.unicesumar.time5.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_bairro")
public class Bairro extends SuperEntidade {

    private String nome;

    @ManyToOne()
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    @ManyToMany()
    @JoinTable(name = "tbcad_logradouro_bairro", joinColumns = { @JoinColumn(name = "id_bairro")}, inverseJoinColumns = { @JoinColumn(name = "id_logradouro")})
    private List<Logradouro> logradouros;
/*
    @OneToMany(mappedBy = "bairro")
    private List<Endereco> enderecos;
*/
    public Bairro() {
        this.logradouros = new ArrayList<>();
    //    this.enderecos = new ArrayList<>();
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
    

    public List<Logradouro> getLogradouros() {
        return logradouros;
    }

    public void setLogradouros(List<Logradouro> logradouros) {
        this.logradouros = logradouros;
    }
}
