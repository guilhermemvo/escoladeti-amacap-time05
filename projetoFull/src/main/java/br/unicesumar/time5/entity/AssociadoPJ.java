package br.unicesumar.time5.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "tbcad_associado_pessoa_juridica")
public class AssociadoPJ extends SuperEntidade {
	
	
	@Column(name = "ativo")
	private boolean ativo;
	
	@Column()
	private String nome;
	
	@Column(name = "valor_contribuicao")
	private float valorContribuicao;

	@OneToOne
	@JoinColumn(name = "id_pessoa")
	private PessoaJuridica pessoaJuridica;
	
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

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	
}


