package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbcad_associado_pessoa_fisica")
public class AssociadoPF extends SuperEntidade {
	
	
	@Column(name = "ativo")
	private boolean ativo;
	
	@Column()
	private String nome;
	
	@Column(name = "valor_contribuicao")
	private float valorContribuicao;
	

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaFisica pessoaFisica;
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public float getValorContribuicao() {
		return valorContribuicao;
	}

	public void setValorContribuicao(float valorContribuicao) {
		this.valorContribuicao = valorContribuicao;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}
