package br.com.dh.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Categoria;
import br.com.dh.model.Movimentacao;
import br.com.dh.services.CategoriaService;
import br.com.dh.services.MovimentacaoService;

public class MovimentacaoDto {
	
	private TipoMovimentacao tipo;
	
	private String descricao;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	private LocalDate dataCriacao = LocalDate.now();
	
//	@Min(value = 1)
//	@NotBlank(message = "O id da categoria não pode ser vazio ou nulo")
	private String idCategoria;
	
	public MovimentacaoDto() {}
	
	public MovimentacaoDto(TipoMovimentacao tipo, String descricao, BigDecimal valor, String categoria) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.idCategoria = categoria;
	}
	
	public MovimentacaoDto(Movimentacao movimentacao) {
		this.tipo = movimentacao.getTipo();
		this.descricao = movimentacao.getDescricao();
		this.valor = movimentacao.getValor();
		this.idCategoria = movimentacao.getCategoria().getId().toString();
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

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public static List<MovimentacaoDto> converter(List<Movimentacao> movimentacoes) {
		return movimentacoes.stream().map(MovimentacaoDto::new).collect(Collectors.toList());
	}

	public Movimentacao converter(CategoriaService service) {
		Optional<Categoria> categoria = service.buscarPorId(Long.parseLong(this.idCategoria));
		if (!categoria.isPresent())
			return null;
		return new Movimentacao(this.tipo, this.descricao, this.valor, categoria.get());
	}
	
	public Movimentacao converter(Categoria categoria) {
		return new Movimentacao(this.tipo, this.descricao, this.valor, categoria);
	}

	public Movimentacao atualizar(Long id, MovimentacaoService service) {
		Optional<Movimentacao> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			Movimentacao movimentacao = optional.get();
			//movimentacao.setCategoria();
			movimentacao.setDescricao(this.descricao);
			movimentacao.setTipo(this.tipo);
			movimentacao.setValor(this.valor);
			return movimentacao;			
		}
		return null;
	}
	
}
