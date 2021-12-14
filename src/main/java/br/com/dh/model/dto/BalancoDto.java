package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.dh.model.Movimentacao;

public class BalancoDto {
	
	private BigDecimal valorTotalEntradas = BigDecimal.ZERO;
	private BigDecimal valorTotalSaidas = BigDecimal.ZERO;
	private List<Movimentacao> movimentacoes = new ArrayList<>();
	
	public BalancoDto() {}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
	
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public BigDecimal getValorTotalEntradas() {
		return valorTotalEntradas;
	}
	
	public void setValorTotalEntradas(BigDecimal valorTotalEntradas) {
		this.valorTotalEntradas = valorTotalEntradas;
	}
	
	public BigDecimal getValorTotalSaidas() {
		return valorTotalSaidas;
	}
	
	public void setValorTotalSaidas(BigDecimal valorTotalSaídas) {
		this.valorTotalSaidas = valorTotalSaídas;
	}

}
