    package br.unicesumar.time5.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "tbcad_pais")
public class Pais extends SuperEntidade {


	    @IndexColumn(name = "id")
	
	    private String nome;
	    @Column(name = "codigo_ibge")
	    private long codigoIBGE;
	    private String sigla;
	
	    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pais")
	    private List<UnidadeFederativa> unidadefederativa;
	
	    public String getNome() {
	        return nome;
	    }
	
	    public void setNome(String nome) {
	        this.nome = nome;
	    }
	
	    public long getCodigoIBGE() {
	        return codigoIBGE;
	    }
	
	    public void setCodigoIBGE(long codigoIBGE) {
	        this.codigoIBGE = codigoIBGE;
	    }
	
	    public String getSigla() {
	        return sigla;
	    }
	
	    public void setSigla(String sigla) {
	        this.sigla = sigla;
	    }


}
