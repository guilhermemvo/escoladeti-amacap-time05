package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_logradouro")
public class Logradouro extends SuperEntidade {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "id_tipo_logradouro")
    private TipoLogradouro tipologradouro;

    @ManyToMany
    @JoinTable(name = "tbcad_logradouro_bairro", joinColumns = { @JoinColumn(name = "id_bairro") }, inverseJoinColumns = { @JoinColumn(name = "id_logradouro") })
    private List<Bairro> bairros;

    @OneToMany(fetch = FetchType.EAGER ,mappedBy = "logradouro")
    @Column(name = "id_endereco")
    private List<Endereco> enderecos;

    private String nome;

    public Logradouro() {
        bairros = new ArrayList<>();
        enderecos = new ArrayList<>();
    }

    public List<Bairro> getBairros() {
        return bairros;
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }

    public TipoLogradouro getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(TipoLogradouro tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> Enderecos) {
        this.enderecos = Enderecos;
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
