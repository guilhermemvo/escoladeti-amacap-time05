package br.unicesumar.time5.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author NuriellyCaroline
 */
@Entity
@Table(name = "tbcad_evento")
public class Evento extends SuperEntidade {

    @IndexColumn(name = "id")
    private String nome;
    private Integer quantidade_vagas;
    private String descricao;
    private String local_evento;
    private Double valor;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_fim;

    @ManyToOne
    @JoinColumn(name = "id_instrutor")
    private Instrutor instrutor;

    private Double cargaHoraria;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_evento_agenda_evento", joinColumns = {
        @JoinColumn(name = "id_evento")}, inverseJoinColumns = {
        @JoinColumn(name = "id_agenda_evento")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AgendaEvento> agendaEvento;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_endereco_evento", joinColumns = {
        @JoinColumn(name = "id_evento")}, inverseJoinColumns = {
        @JoinColumn(name = "id_endereco")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Endereco> enderecos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tbcad_foto_evento", joinColumns = {
        @JoinColumn(name = "id_evento")}, inverseJoinColumns = {
        @JoinColumn(name = "id_foto")})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Foto> fotos;

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public Double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Double cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade_vagas() {
        return quantidade_vagas;
    }

    public void setQuantidade_vagas(Integer quantidade_vagas) {
        this.quantidade_vagas = quantidade_vagas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLocal_evento() {
        return local_evento;
    }

    public void setLocal_evento(String local_evento) {
        this.local_evento = local_evento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        data_inicio.setDate(data_inicio.getDate() + 1);
        this.data_inicio = data_inicio;
    }

    public Date getData_fim() {
        return data_fim;
    }

    public void setData_fim(Date data_fim) {
        data_fim.setDate(data_fim.getDate() + 1);
        this.data_fim = data_fim;
    }

    public List<AgendaEvento> getAgendaEvento() {
        return agendaEvento;
    }

    public void setAgendaEvento(List<AgendaEvento> agendaEvento) {
        this.agendaEvento = agendaEvento;
    }
}
