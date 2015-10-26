package br.unicesumar.time5.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "tbcad_pedido")
public class Pedido extends SuperEntidade implements Serializable {

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataPedido;

    private String observacao;

    @Column(name="status", nullable=true, length=30)
    private String status = "AGUARDANDO OFICIO";
    @ManyToOne()
    @JoinColumn(name = "idSolicitante")
    private PessoaFisica solicitante;

    @ManyToOne
    @JoinColumn(name = "idEscola")
    private Escola escola;

    @ManyToOne()
    @JoinColumn(name = "idAluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "idProfessor")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "idOficio")
    private Arquivo oficio;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_pedido_detalhe", joinColumns = {
        @JoinColumn(name = "id_pedido")}, inverseJoinColumns = {
        @JoinColumn(name = "id_detalhe")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PedidoMaterial> pedidoMaterial;

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }


    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pessoa getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(PessoaFisica solicitante) {
        this.solicitante = solicitante;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Arquivo getOficio() {
        return oficio;
    }

    public void setOficio(Arquivo oficio) {
        this.oficio = oficio;
    }

    public List<PedidoMaterial> getPedidoMaterial() {
        return pedidoMaterial;
    }

    public void setPedidoMaterial(List<PedidoMaterial> pedidoMaterial) {
        this.pedidoMaterial = pedidoMaterial;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
