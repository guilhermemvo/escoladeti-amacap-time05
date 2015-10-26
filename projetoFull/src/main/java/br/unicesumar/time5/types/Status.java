package br.unicesumar.time5.types;

public enum Status {

    ATIVO("ATIVO"), INATIVO("INATIVO"),CANCELADO("CANCELADO"),PRODUCAO("PRODUCAO"),CONCLUIDO("CONCLUIDO");

    Status(String descricao) {
        this.descricao = descricao;
    }

    private final String descricao;

    public String getDescricao() {
        return descricao;
    }

}
