package br.unicesumar.time5.entity;

import static br.unicesumar.time5.util.nvl.NvlUtil.nvlToEmpty;
import static liquibase.util.MD5Util.computeMD5;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.IndexColumn;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "tbcad_usuario")
public class Usuario extends SuperEntidade {

    @IndexColumn(name = "id")
    
    @NotEmpty
    private String senha;
    
    @NotNull
    private Boolean ativo;
    
    @NotEmpty
    private String login;
    
    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Funcionario funcionario;
    
    @ManyToOne
    @JoinColumn(name = "id_perfil_acesso")
    private PerfilAcesso perfilAcesso;

    
    
    
    public PerfilAcesso getPerfilAcesso() {
        return perfilAcesso;
    }

    public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
        this.perfilAcesso = perfilAcesso;
    }

  

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

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
