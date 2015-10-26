package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_volume")
public class Volume extends SuperEntidade {

    @ManyToOne
    @JsonIgnore
    private PedidoMaterial pedidoDetalheMaterial;

    private String identificacao;
    private Integer status;
    @Column(name = "pagina_inicial")
    private Integer paginaInicial;
    @Column(name = "pagina_final")
    private Integer paginaFinal;
    @OneToOne()
    @JoinColumn(name = "id_transportadora")
    private Transportadora transportadora;
    @OneToOne()
    @JoinColumn(name = "id_unidade_producao")
    private UnidadeProducao unidadeProducao;
    
    private String colaborador;
    private String observacao;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_alteracao;
    

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(Integer paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    public Integer getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(Integer paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public UnidadeProducao getUnidadeProducao() {
        return unidadeProducao;
    }

    public void setUnidadeProducao(UnidadeProducao unidadeProducao) {
        this.unidadeProducao = unidadeProducao;
    }

    public PedidoMaterial getMaterial() {
        return pedidoDetalheMaterial;
    }

    public void setMaterial(PedidoMaterial pedidoDetalheMaterial) {
        this.pedidoDetalheMaterial = pedidoDetalheMaterial;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public Date getData_alteracao() {
        return data_alteracao;
    }

    public void setData_alteracao(Date data_alteracao) {
        this.data_alteracao = data_alteracao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }   
}
