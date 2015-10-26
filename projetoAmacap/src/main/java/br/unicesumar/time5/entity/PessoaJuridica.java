package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_pessoa_juridica")
public class PessoaJuridica extends Pessoa {

    private String cnpj;
    @Column(name = "apelido_fantasia")
    private String apelidoFantasia;
    @Column(name = "nome_razao")
    private String nomeRazao;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getApelidoFantasia() {
        return apelidoFantasia;
    }

    public void setApelidoFantasia(String apelidoFantasia) {
        this.apelidoFantasia = apelidoFantasia;
    }

    public String getNomeRazao() {
        return nomeRazao;
    }

    public void setNomeRazao(String nomeRazao) {
        this.nomeRazao = nomeRazao;
    }

}
