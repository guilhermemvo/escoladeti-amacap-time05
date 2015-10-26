package br.unicesumar.escoladeti.entity;

import javax.persistence.Entity;

@Entity
public class Disciplina extends SuperEntidade implements Comparable<Disciplina> {
	
	private String nome;
	
	public Disciplina() {
	}

	public Disciplina(String nome) {
		this(null, nome);
	}

	public Disciplina(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int compareTo(Disciplina outraDisciplina) {
		if (outraDisciplina == null)
			return -1;
		
		if (getNome() == null && outraDisciplina.getNome() == null)
			return 0;
		
		if (outraDisciplina.getNome() == null)
			return -1;
		
		if (getNome() == null)
			return 1;
		
		return getNome().compareTo(outraDisciplina.getNome());
	}

}
