package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

@Entity
@Table(name = "tbcad_endereco")
public class Endereco extends SuperEntidade {

    @ManyToOne
    @JoinColumn(name = "id_bairro")
    private Bairro bairro;

    @ManyToOne
    @JoinColumn(name = "id_logradouro")
    private Logradouro logradouro;

    @OneToMany(mappedBy = "endereco")
    @Column(name = "id_pessoa_endereco")
    private List<PessoaEndereco> pessoaEndereco;

    private String numero;
    private String complemento;

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

    public List<PessoaEndereco> getPessoaendereco() {
        return pessoaEndereco;
    }

    public void setPessoaendereco(List<PessoaEndereco> pessoaEndereco) {
        this.pessoaEndereco = pessoaEndereco;
    }
}
