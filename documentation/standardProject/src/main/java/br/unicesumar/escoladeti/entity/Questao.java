package br.unicesumar.escoladeti.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Questao extends SuperEntidade {

	@NotEmpty
	private String enunciado;
	private Integer nivelDificuldade;
	
	@Enumerated(EnumType.STRING)
	private TipoQuestao tipo;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "QUESTAODISCIPLINA", 
				joinColumns = @JoinColumn(name = "QUESTAO_ID", referencedColumnName = "ID"), 
				inverseJoinColumns = @JoinColumn(name = "DISCIPLINA_ID", referencedColumnName = "ID"))
	private List<Disciplina> disciplinas;

	public Questao(TipoQuestao tipo) {
		super();
		this.tipo = tipo;
	}
	
	@Override
	public void avoidLazyFail() {
		getDisciplinas().size();
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public List<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = new ArrayList<>();
			
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
	
	public Integer getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(Integer nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}
	
	public TipoQuestao getTipo() {
		return tipo;
	}
	
	@Override
	public String label() {
		return getEnunciado();
	}
	
	@Override
	public String toString() {
		return getEnunciado();
	}
}
