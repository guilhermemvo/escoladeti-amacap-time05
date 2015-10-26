package br.unicesumar.time5.types;

public enum Sexo {

    m("Masculino"), f("Feminino");

    Sexo(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

}
