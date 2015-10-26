package br.unicesumar.time5.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tbcad_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends SuperEntidade {

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro = new Date();

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_telefone_pessoa", joinColumns = {
        @JoinColumn(name = "id_pessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "id_telefone")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Telefone> telefones;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_email_pessoa", joinColumns = {
        @JoinColumn(name = "id_pessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "id_email")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_endereco_pessoa", joinColumns = {
        @JoinColumn(name = "id_pessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "id_endereco")})
    @LazyCollection(LazyCollectionOption.FALSE) 
    private List<Endereco> enderecos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    
    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

  
    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> email) {
        this.emails = email;
    }
}
