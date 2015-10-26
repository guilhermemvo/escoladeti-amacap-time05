package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_materia_prima_material")
public class MateriaPrimaMaterial extends SuperEntidade {

    @ManyToOne
    @JoinColumn(name = "id_materia_prima")
    private MateriaPrima materiaprima;

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
    private int quantidade;

    public MateriaPrima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(MateriaPrima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
