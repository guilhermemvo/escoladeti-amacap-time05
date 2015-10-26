package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends SuperEntidade {

    @OneToMany(mappedBy = "pessoa")
    @Column(name = "id_pessoa_endereco")
    private List<PessoaEndereco> pessoaEndereco;
    
    @OneToMany(mappedBy = "pessoa")
    @Column(name = "id_email")
    private List<Email> emails;
    
    @OneToMany(mappedBy = "pessoas")
    @Column(name = "id_telefone")
    private List<Telefone> telefones;

    @Column(name = "tipo_pessoa")
    private Integer tipoPessoa;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    public Integer getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(Integer tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<PessoaEndereco> getPessoaEndereco() {
        return pessoaEndereco;
    }

    public void setPessoaEndereco(List<PessoaEndereco> pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

}
