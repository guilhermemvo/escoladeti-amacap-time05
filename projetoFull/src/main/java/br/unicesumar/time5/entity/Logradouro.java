package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_logradouro")
public class Logradouro extends SuperEntidade {

    private String nome;

    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_tipo_logradouro")
    private TipoLogradouro tipoLogradouro;

    /*
     @ManyToMany()
     @LazyCollection(LazyCollectionOption.FALSE)
     @JoinTable(name = "tbcad_logradouro_bairro",
     joinColumns = @JoinColumn(name = "id_logradouro", referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(name = "id_bairro", referencedColumnName = "id"))
     private List<Bairro> bairros;

     public List<Bairro> getBairros() {
     return bairros;
     }

     public void setBairros(List<Bairro> bairros) {
     this.bairros = bairros;
     }
     */
    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public TipoLogradouro getTipoLogradouro() {
        return tipoLogradouro;
    }

    public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
        this.tipoLogradouro = tipoLogradouro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
