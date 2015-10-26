package br.unicesumar.time5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "tbcad_data_agenda")
public class AgendaEvento extends SuperEntidade {
   
    @ManyToOne
    @JsonIgnore
    private Evento evento;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data;
    private Boolean manha;
    private Boolean tarde;
    private Boolean noite;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        data.setDate(data.getDate() + 1);
        this.data = data;
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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

}
