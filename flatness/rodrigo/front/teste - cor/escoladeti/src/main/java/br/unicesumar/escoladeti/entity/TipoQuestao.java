package br.unicesumar.escoladeti.entity;

public enum TipoQuestao {

	DISCURSIVA("Discursiva", "questaoDiscursiva"),
	OBJETIVA("Objetiva", "questaoObjetiva");
	
	private final String descricao;
	private final String context;

	private TipoQuestao(String descricao, String context) {
		this.descricao = descricao;
		this.context = context;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getContext() {
		return context;
	}
}
