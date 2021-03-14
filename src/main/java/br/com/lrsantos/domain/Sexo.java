package br.com.lrsantos.domain;

public enum Sexo {
	M("Masculino"), F("Feminino"), O("Outro");
	
	private String descricao;

	private Sexo(String codigo) {
		this.descricao = codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
