package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author PauloHenrique
 */
@Entity
@Table(name = "tbcad_material")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
    @Type(value = Livro.class, name = "livro"),
    @Type(value = Outros.class, name = "outros")})
public class Material extends SuperEntidade {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_arquivo_material", joinColumns = {
        @JoinColumn(name = "id_material")}, inverseJoinColumns = {
        @JoinColumn(name = "id_arquivo")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Arquivo> arquivos;


    @Column(name = "nome")
    private String nome;

    @Column(name = "observacao")
    private String observacao;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_materia_prima_material_material", joinColumns = {
        @JoinColumn(name = "id_material")}, inverseJoinColumns = {
        @JoinColumn(name = "id_materia_prima_material")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<MateriaPrimaMaterial> materiaprima;

    public List<MateriaPrimaMaterial> getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(List<MateriaPrimaMaterial> materiaprima) {
        this.materiaprima = materiaprima;
    }
   
    
    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
