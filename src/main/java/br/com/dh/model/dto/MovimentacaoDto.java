package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Categoria;

public class MovimentacaoDto {
	
	private TipoMovimentacao tipo;
	
	private String descricao;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	private LocalDate dataCriacao;
	
	private Categoria categoria;

	public MovimentacaoDto(TipoMovimentacao tipo, String descricao, BigDecimal valor, Categoria categoria) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.dataCriacao = LocalDate.now();
		this.categoria = categoria;
	}

	public TipoMovimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
