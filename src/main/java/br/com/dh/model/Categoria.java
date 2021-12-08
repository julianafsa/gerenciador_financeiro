package br.com.dh.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    
    @Column(name = "limite_mensal", nullable = true)
    private BigDecimal limiteMensal;
    
	public Categoria() {
	}

	public Categoria(String nome, BigDecimal limiteMensal) {
		super();
		this.nome = nome;
		this.limiteMensal = limiteMensal;
	}
	
	public Long getId() {
		return id;
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
	
}