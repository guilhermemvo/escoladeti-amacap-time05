package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tbcad_pedido_material")
public class PedidoMaterial extends SuperEntidade {

    @OneToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_pedido_detalhe_volume", joinColumns = {
        @JoinColumn(name = "id_detalhe")}, inverseJoinColumns = {
        @JoinColumn(name = "id_volume")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Volume> volumes;

    private Integer paginaInicial;
    private Integer paginaFinal;

    @OneToOne
    @JoinColumn(name = "id_tipo_material")
    private TipoMaterial tipoMaterial;

    private boolean recebido = false;
        
    @ManyToOne
    @JoinColumn(name = "id_unidadeproducao")
    private UnidadeProducao unidadeProducao = null;

    public boolean isRecebido() {
        return recebido;
    }

    public void setRecebido(boolean recebido) {
        this.recebido = recebido;
    }

    public UnidadeProducao getUnidadeProducao() {
        return unidadeProducao;
    }

    public void setUnidadeProducao(UnidadeProducao unidadeProducao) {
        this.unidadeProducao = unidadeProducao;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Integer getPaginaFinal() {
        return paginaFinal;
    }

    public void setPaginaFinal(Integer paginaFinal) {
        this.paginaFinal = paginaFinal;
    }

    public Integer getPaginaInicial() {
        return paginaInicial;
    }

    public void setPaginaInicial(Integer paginaInicial) {
        this.paginaInicial = paginaInicial;
    }

    @Column(name = "idpedido")
    private String idPedido;

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
