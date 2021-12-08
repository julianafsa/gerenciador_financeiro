package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.dh.model.Categoria;

public class CategoriaDto {

    private String nome;
    private BigDecimal limiteMensal;
    
	public CategoriaDto() {
	}

	public CategoriaDto(String nome, BigDecimal limiteMensal) {
		this.nome = nome;
		this.limiteMensal = limiteMensal;
	}
	
	public CategoriaDto(Categoria categoria) {
		this.nome = categoria.getNome();
		this.limiteMensal = categoria.getLimiteMensal();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getLimiteMensal() {
		return limiteMensal;
	}

	public void setLimiteMensal(BigDecimal limiteMensal) {
		this.limiteMensal = limiteMensal;
	}

	public static List<CategoriaDto> converter(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
	
}
