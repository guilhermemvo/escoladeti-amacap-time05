package br.unicesumar.time5.vo;

public class AssociadoPfVO {

    private Long id;
    private Boolean ativo;
    private float valorContribuicao;
    private Long idPessoaFisica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public float getValorContribuicao() {
        return valorContribuicao;
    }

    public void setValorContribuicao(float valorContribuicao) {
        this.valorContribuicao = valorContribuicao;
    }

    public Long getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Long idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

}
