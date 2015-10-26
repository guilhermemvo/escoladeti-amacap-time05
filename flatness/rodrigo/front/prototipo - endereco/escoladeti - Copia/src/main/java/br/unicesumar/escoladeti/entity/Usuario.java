package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.nvl.NvlUtil.nvlToEmpty;
import static liquibase.util.MD5Util.computeMD5;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Usuario extends SuperEntidade {

	@NotEmpty
	private String login;
	@NotEmpty
	private String senha;
	@NotNull
	private Boolean ativo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = computeMD5(nvlToEmpty(senha));
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String label() {
		return getLogin();
	}

}
