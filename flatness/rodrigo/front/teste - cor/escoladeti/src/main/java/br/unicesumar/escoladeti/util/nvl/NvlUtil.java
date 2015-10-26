package br.unicesumar.escoladeti.util.nvl;

import br.unicesumar.escoladeti.entity.SuperEntidade;

public class NvlUtil {
	
	public static String nvlToEmpty(String value) {
		return nvl(value, "");
	}

	public static String nvl(String value, String defaultValue) {
		return value != null ? value : defaultValue;
	}
	
	public static boolean nvlToFalse(Boolean value) {
		return nvl(value, false);
	}

	public static boolean nvlToTrue(Boolean value) {
		return nvl(value, true);
	}
	public static Integer nvlToOne(Integer value) {
		return nvl(value, 1);
	}
	
	public static Integer nvl(Integer value, int defaultValue) {
		return value != null ? value.intValue() : defaultValue;
	}

	public static boolean nvl(Boolean value, boolean defaultValue) {
		return value != null ? value.booleanValue() : defaultValue;
	}

	public static String nvlIdEmpty(SuperEntidade entidade) {
		if (entidade == null)
			return "";
		
		return entidade.getId() != null ? entidade.getId().toString() : "";
	}

}
