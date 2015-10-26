package br.unicesumar.escoladeti.entity;

import static br.unicesumar.escoladeti.util.nanotime.NanotimeUtil.nanotime;
import br.unicesumar.escoladeti.entity.SuperEntidade;

public class EntidadeDummy extends SuperEntidade implements Cloneable {
	
	private String value;

	public EntidadeDummy(String value) {
		super();
		this.value = value;
	}
	
	public String value() {
		return value;
	}
	
	public EntidadeDummy copy() {
		EntidadeDummy copy = new EntidadeDummy(value);
		
		copy.nanotime = nanotime;
		copy.id = id;
		
		return copy;
	}

	public void updateNanotime() {
		nanotime = nanotime();
	}

	@Override
	public String label() {
		return value();
	}

}
