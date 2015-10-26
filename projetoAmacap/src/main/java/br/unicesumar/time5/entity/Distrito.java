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
@Table(name = "tbcad_distrito")
public class Distrito extends SuperEntidade {

    @IndexColumn(name = "id")
    
    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;

    private String nome;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "inicio_vigencia")
    private Date inicioVigencia;

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNome() {
        return nome;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
