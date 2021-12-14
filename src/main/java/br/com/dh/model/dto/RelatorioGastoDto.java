package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.dh.model.Movimentacao;

public class RelatorioGastoDto {
	
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private BigDecimal valorTotalgastos = BigDecimal.ZERO;
	private List<Movimentacao> saidas = new ArrayList<>();
	
	public RelatorioGastoDto() {}
	
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public BigDecimal getValorTotalSaidas() {
		return valorTotalgastos;
	}
	
	public void setValorTotalSaidas(BigDecimal valorTotalSaídas) {
		this.valorTotalgastos = valorTotalSaídas;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return saidas;
	}
	
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.saidas = movimentacoes;
	}

}
