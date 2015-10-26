package br.unicesumar.escoladeti.entity;

import static java.lang.String.format;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Alternativa extends SuperEntidade implements Comparable<Alternativa> {

	@NotEmpty
	private String texto;
	@NotNull
	private Boolean correta;
	@NotNull
	private Integer ordem;
	
	@ManyToOne
	@JsonIgnore
	private QuestaoObjetiva questao;

	public Alternativa() {
	}

	public Alternativa(QuestaoObjetiva questao) {
		this.questao = questao;
		correta = false;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public QuestaoObjetiva getQuestao() {
		return questao;
	}

	public void setQuestao(QuestaoObjetiva questao) {
		this.questao = questao;
	}

	public void subir() {
		this.ordem++;
	}

	public void descer() {
		this.ordem--;
	}

	@Override
	public int compareTo(Alternativa o) {
		if (o == null)
			return -1;
		
		if (getOrdem() == null && o.getOrdem() == null)
			return 0;
		
		if (o.getOrdem() == null)
			return -1;
		
		if (getOrdem() == null)
			return 1;
		
		return getOrdem().compareTo(o.getOrdem());
	}
	
	@Override
	public String toString() {
		return format("(%s) %d - %s", corretaTexto(), getOrdem(), getTexto());
	}

	public String corretaTexto() {
		return getCorreta() ? "Correta" : "Incorreta";
	}
	
}
