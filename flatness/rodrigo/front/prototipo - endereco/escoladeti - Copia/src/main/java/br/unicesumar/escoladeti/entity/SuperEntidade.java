package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.nanotime.NanotimeUtil.nanotime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.unicesumar.escoladeti.util.tostring.ToStringUtil;

@MappedSuperclass
public abstract class SuperEntidade {

	static final Logger logger = LoggerFactory.getLogger(SuperEntidade.class);

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected Long id;
	@Transient
	protected Long nanotime;
	
	public SuperEntidade() {
		this.nanotime = nanotime();
	}

	public void avoidLazyFail() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ToStringUtil.toString(this);
	}

	@JsonIgnore
	public boolean isNew() {
		return this.id == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? this.nanotime.hashCode() : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		
		if (this == obj)
			return true;
		
		if (getClass() != obj.getClass())
			return false;
		
		SuperEntidade other = (SuperEntidade) obj;
		
		if (this.id == null && other.id == null) {
			return this.nanotime == other.nanotime;
		}
		
		if (id == null) {
			return false;
		}
		
		return id.equals(other.id);
	}
	
	public String label() {
		return toString();
	}
	
	public String value() {
		return label();
	}

	public void verificarConsistencia() {
		logger.debug("Iniciando verificação de consistência em {}", this);
		
		executarVerificacaoDeConsistenciaEspecializada();
		
		logger.debug("Não foi detectada nenhuma inconsistência");
	}

	protected void executarVerificacaoDeConsistenciaEspecializada() {
	}
}
