package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.dh.model.Movimentacao;

public class BalancoSaidasDto {
	
	private BigDecimal valorTotalSaidas = BigDecimal.ZERO;
	private List<Movimentacao> saidas = new ArrayList<>();
	
	public BalancoSaidasDto() {}
	
	public List<Movimentacao> getSaidas() {
		return saidas;
	}
	
	public void setSaidas(List<Movimentacao> saidas) {
		this.saidas = saidas;
	}
	
	public BigDecimal getValorTotalSaidas() {
		return valorTotalSaidas;
	}
	
	public void setValorTotalSaidas(BigDecimal valorTotalSaidas) {
		this.valorTotalSaidas = valorTotalSaidas;
	}

}
