package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "tbcad_endereco")
public class Endereco extends SuperEntidade {

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_logradouro")
    private Logradouro logradouro;
    
    @ManyToOne
    @JoinColumn(name = "id_tipologradouro")
    private TipoLogradouro tipologradouro;    
/*
    @OneToMany(mappedBy = "endereco")
    @Column(name = "id_pessoa_endereco")
    private List<PessoaEndereco> pessoaEndereco;
*/
    
    @ManyToOne
    @JsonIgnore
    private Pessoa pessoa;
    
    private String numero;
    private String complemento;
    private String cep;
    
    @ManyToOne
    @JoinColumn(name = "id_pais")    
    private Pais pais;
    
    @ManyToOne
    @JoinColumn(name = "id_unidade_federativa")    
    private UnidadeFederativa uf;
    
    @ManyToOne
    @JoinColumn(name = "id_cidade")    
    private Cidade cidade;

    public TipoLogradouro getTipologradouro() {
        return tipologradouro;
    }

    public void setTipologradouro(TipoLogradouro tipologradouro) {
        this.tipologradouro = tipologradouro;
    }

    
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public UnidadeFederativa getUf() {
        return uf;
    }

    public void setUf(UnidadeFederativa uf) {
        this.uf = uf;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    
}
