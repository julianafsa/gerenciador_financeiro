package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.dh.model.Movimentacao;

public class BalancoEntradasDto {
	
	private BigDecimal valorTotalEntradas = BigDecimal.ZERO;
	private List<Movimentacao> entradas = new ArrayList<>();
	
	public BalancoEntradasDto() {}
	
	public List<Movimentacao> getEntradas() {
		return entradas;
	}
	
	public void setEntradas(List<Movimentacao> movimentacoes) {
		this.entradas = movimentacoes;
	}
	
	public BigDecimal getValorTotalEntradas() {
		return valorTotalEntradas;
	}
	
	public void setValorTotalEntradas(BigDecimal valorTotalEntradas) {
		this.valorTotalEntradas = valorTotalEntradas;
	}

}
