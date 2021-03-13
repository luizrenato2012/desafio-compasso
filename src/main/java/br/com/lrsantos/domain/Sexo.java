package br.com.lrsantos.domain;

public enum Sexo {
	MASCULINO("M"), FEMININO("f"), OUTROS("O");
	
	private String codigo;

	private Sexo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
}
