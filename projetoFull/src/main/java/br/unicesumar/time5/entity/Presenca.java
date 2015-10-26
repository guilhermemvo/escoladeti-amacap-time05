package br.unicesumar.time5.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbcad_pessoa_presenca")
public class Presenca extends SuperEntidade{
    
    @OneToOne
    private AgendaEvento agendaEvento;
    
    @OneToOne
    private Inscricao inscricao;
    
    private Boolean manha;
    private Boolean tarde;
    private Boolean noite;

    public AgendaEvento getAgendaEvento() {
        return agendaEvento;
    }

    public void setAgendaEvento(AgendaEvento agendaEvento) {
        this.agendaEvento = agendaEvento;
    }

    public Inscricao getInscricao() {
        return inscricao;
    }

    public void setInscricao(Inscricao inscricao) {
        this.inscricao = inscricao;
    }

    public Boolean isManha() {
        return manha;
    }

    public void setManha(Boolean manha) {
        this.manha = manha;
    }

    public Boolean isTarde() {
        return tarde;
    }

    public void setTarde(Boolean tarde) {
        this.tarde = tarde;
    }

    public Boolean isNoite() {
        return noite;
    }

    public void setNoite(Boolean noite) {
        this.noite = noite;
    }
    
    
}
