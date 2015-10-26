package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author johnLima
 */
@Entity
@Table(name = "tbcad_livro")
@PrimaryKeyJoinColumn(name = "id")
public class Livro extends Material {

    @Column(name = "autor")
    private String autor;

    @Column(name = "edicao")
    private String edicao;

    @Column(name = "type")
    private String type = "livro";

    public String getType() {
        return type;
    }

    @Column(name = "serie")
    private String serie;

    @Column(name = "ano")
    private String ano;

    @Column(name = "pnld")
    private String pnld;

    @Column(name = "codigo_prateleira")
    private String codigoPrateleira;

    @ManyToOne
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getPnld() {
        return pnld;
    }

    public void setPnld(String pnld) {
        this.pnld = pnld;
    }

    public String getCodigoPrateleira() {
        return codigoPrateleira;
    }

    public void setCodigoPrateleira(String codigoPrateleira) {
        this.codigoPrateleira = codigoPrateleira;
    }

}
