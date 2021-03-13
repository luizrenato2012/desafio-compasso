package br.com.lrsantos.infraestructure.util;

import org.springframework.util.ObjectUtils;

public class ValueObjectUtil {

	/**
	 * Verifica validade da String 
	 * @param value
	 * @return retorna próprio valor caso seja não nula ou null caso contrário
	 */
	public static String getValueString(String value) {
		return !ObjectUtils.isEmpty(value) ? value : null;
	}
	
	/**
	 * Verifica validade da String 
	 * @param value
	 * @return retorna próprio valor caso seja não nula ou espeficado caso contrário
	 */
	public static Object getValueStringOrNull(String value, Object result) {
		return !ObjectUtils.isEmpty(value) ? result : null;
	}
	
	/**
	 * Verifica validade da String 
	 * @param <T>
	 * @param value
	 * @return retorna próprio valor caso seja não nula ou espeficado caso contrário
	 */
	public static <T,X> X getValueOrNull(T value, X result) {
		return value !=null ? result : null;
	}
	
	/**
	 * Verifica validade da Enum 
	 * @param value
	 * @return retorna próprio valor caso seja não nula ou espeficado caso contrário
	 */
	public static String getValueOrNull(Enum value, String result) {
		return !ObjectUtils.isEmpty(value) ? result : null;
	}
}
