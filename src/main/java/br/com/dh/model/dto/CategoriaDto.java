package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.dh.model.Categoria;
import br.com.dh.services.CategoriaService;

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

	public static Categoria converter(CategoriaDto categoriaInput) {
		return new Categoria(categoriaInput.getNome(), categoriaInput.getLimiteMensal());
	}

	public Categoria atualizar(Long id, CategoriaService service) {
		Optional<Categoria> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			Categoria categoria = optional.get();
			categoria.setLimiteMensal(this.limiteMensal);
			categoria.setNome(this.nome);
			return categoria;			
		}
		return null;
	}
	
}
