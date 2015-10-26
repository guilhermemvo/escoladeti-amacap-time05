package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_arquivo")
public class Arquivo extends SuperEntidade {

    private String origem;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id_tipo_material")
    private TipoMaterial tipomaterial;

    public TipoMaterial getTipomaterial() {
        return tipomaterial;
    }

    public void setTipomaterial(TipoMaterial tipomaterial) {
        this.tipomaterial = tipomaterial;
    }

    @ManyToOne
    @JsonIgnore
    private Material material;

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
