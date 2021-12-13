package br.com.dh.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.dh.enumerations.TipoMovimentacao;

@Entity
@Table(name = "movimentacao")
public class Movimentacao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "tipo", nullable = false)
	private TipoMovimentacao tipo;
	
	private String descricao;
	
	private BigDecimal valor = BigDecimal.ZERO;
	
	@Column(name = "data_criacao")
	private LocalDate dataCriacao = LocalDate.now();
	
	@ManyToOne
	@JoinColumn(name = "categoria_fk")
	private Categoria categoria;
	
	public Movimentacao() {
	}

	public Movimentacao(TipoMovimentacao tipo, String descricao, BigDecimal valor, Categoria categoria) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
	}
	
	public Long getId() {
		return id;
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
