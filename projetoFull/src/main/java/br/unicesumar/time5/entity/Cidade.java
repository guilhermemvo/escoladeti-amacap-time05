package br.unicesumar.time5.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "tbcad_cidade")
public class Cidade extends SuperEntidade {

    @IndexColumn(name = "id")
    private String nome;
    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "data_fundacao")
    private Date dataFundacao;

    @Column(name = "codigo_ibge")
    private long codigoIBGE;

    @ManyToOne
    @JoinColumn(name = "id_unidade_federativa")
    private UnidadeFederativa unidadefederativa;

    public UnidadeFederativa getUnidadefederativa() {
        return unidadefederativa;
    }

    public void setUnidadefederativa(UnidadeFederativa unidadefederativa) {
        this.unidadefederativa = unidadefederativa;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        dataFundacao.setDate(dataFundacao.getDate() + 1);
        this.dataFundacao = dataFundacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCodigoIBGE() {
        return codigoIBGE;
    }

    public void setCodigoIBGE(long codigoIBGE) {
        this.codigoIBGE = codigoIBGE;
    }

}
