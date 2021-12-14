package br.com.dh.config.validacao;

import br.com.dh.model.Movimentacao;

public class MyException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Movimentacao movimentacao;

	public MyException(Movimentacao movimentacao, String errorMessage) {
		super(errorMessage);
		this.movimentacao = movimentacao;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

}
