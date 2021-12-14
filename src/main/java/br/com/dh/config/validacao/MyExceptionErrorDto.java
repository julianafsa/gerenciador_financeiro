package br.com.dh.config.validacao;

public class MyExceptionErrorDto {
	
	private String categoria;
	private String limite;
	private String erro;
	
	public MyExceptionErrorDto(String categoria, String limite, String erro) {
		this.categoria = categoria;
		this.limite = limite;
		this.erro = erro;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public String getLimite() {
		return limite;
	}
	
	public void setLimite(String limite) {
		this.limite = limite;
	}
	
	public String getErro() {
		return erro;
	}
	
	public void setErro(String erro) {
		this.erro = erro;
	}

}
