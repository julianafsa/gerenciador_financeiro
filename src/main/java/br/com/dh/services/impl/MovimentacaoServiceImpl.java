package br.com.dh.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Movimentacao;
import br.com.dh.model.dto.BalancoDto;
import br.com.dh.model.dto.BalancoEntradasDto;
import br.com.dh.model.dto.BalancoSaidasDto;
import br.com.dh.model.dto.RelatorioGastoDto;
import br.com.dh.repository.MovimentacaoRepository;
import br.com.dh.services.MovimentacaoService;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {
	
    MovimentacaoRepository repository;
    
    public MovimentacaoServiceImpl(MovimentacaoRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Movimentacao> buscarTodos() {
		return this.repository.findAll();
	}

	@Override
	public Optional<Movimentacao> buscarPorId(Long id) {
		return this.repository.findById(id);
	}

	@Override
	public void editar(Movimentacao movimentacao) {
		Movimentacao movimentacaoVelha = this.repository.findById(movimentacao.getId()).get();
//		String nome = movimentacaoVelha.getNome();
//		BigDecimal limiteMensal = movimentacaoVelha.getLimiteMensal();
//		if (nome != null) {
//			movimentacaoVelha.setNome(nome);
//		}
//		if (limiteMensal != null) {
//			movimentacaoVelha.setLimiteMensal(limiteMensal);
//		}
		this.repository.save(movimentacaoVelha);
	}

	@Override
	public void excluir(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Movimentacao salvar(Movimentacao movimentacao) {
		return this.repository.save(movimentacao);
	}
	
    ////////////////////// BALANCOS //////////////////////
	
	@Override
	public BalancoDto buscarBalanco() {
		List<Movimentacao> movimentacoes = this.repository.findAll();
		BigDecimal somaEntradas = BigDecimal.ZERO;
		BigDecimal somaSaidas = BigDecimal.ZERO;
		for (Iterator<Movimentacao> iterator = movimentacoes.iterator(); iterator.hasNext();) {
			Movimentacao movimentacao = (Movimentacao) iterator.next();
			if (movimentacao.getTipo() == TipoMovimentacao.RECEITA) {
				somaEntradas = somaEntradas.add(movimentacao.getValor());
			} else {
				somaSaidas = somaSaidas.add(movimentacao.getValor());
			}
		}
		BalancoDto balanco = new BalancoDto();
		balanco.setMovimentacoes(movimentacoes);
		balanco.setValorTotalSaidas(somaSaidas);
		balanco.setValorTotalEntradas(somaEntradas);
		return balanco;
	}
	
	@Override
	public BalancoDto buscarBalanco(TipoMovimentacao tipo) {
		List<Movimentacao> movimentacoes = this.repository.buscarBalanco(tipo);
		BigDecimal soma = BigDecimal.ZERO;
		for (Iterator<Movimentacao> iterator = movimentacoes.iterator(); iterator.hasNext();) {
			Movimentacao movimentacao = (Movimentacao) iterator.next();
			soma = soma.add(movimentacao.getValor());
		}
		BalancoDto balanco = new BalancoDto();
		balanco.setMovimentacoes(movimentacoes);
		if (tipo.equals(TipoMovimentacao.DESPESA))
			balanco.setValorTotalSaidas(soma);
		else if (tipo.equals(TipoMovimentacao.RECEITA))
			balanco.setValorTotalEntradas(soma);
		return balanco;
	}

	@Override
	public BalancoEntradasDto buscarBalancoEntradas() {
		List<Movimentacao> movimentacoes = this.repository.buscarBalancoEntradas();
		BigDecimal somaEntradas = BigDecimal.ZERO;
		for (Iterator<Movimentacao> iterator = movimentacoes.iterator(); iterator.hasNext();) {
			Movimentacao movimentacao = (Movimentacao) iterator.next();
			somaEntradas = somaEntradas.add(movimentacao.getValor());
		}
		BalancoEntradasDto balanco = new BalancoEntradasDto();
		balanco.setEntradas(movimentacoes);
		balanco.setValorTotalEntradas(somaEntradas);
		return balanco;
	}

	@Override
	public BalancoSaidasDto buscarBalancoSaidas() {
		List<Movimentacao> movimentacoes = this.repository.buscarBalancoSaidas();
		BigDecimal somaSaidas = BigDecimal.ZERO;
		for (Iterator<Movimentacao> iterator = movimentacoes.iterator(); iterator.hasNext();) {
			Movimentacao movimentacao = (Movimentacao) iterator.next();
			somaSaidas = somaSaidas.add(movimentacao.getValor());
		}
		BalancoSaidasDto balanco = new BalancoSaidasDto();
		balanco.setSaidas(movimentacoes);
		balanco.setValorTotalSaidas(somaSaidas);
		return balanco;
	}
	
    ////////////////////// RELATORIOS //////////////////////

	@Override
	public RelatorioGastoDto relatorioGastoDia(LocalDate data) {
		List<Movimentacao> movimentacoes = this.repository.relatorioGastoDia(data);
		BigDecimal somaSaidas = BigDecimal.ZERO;
		for (Iterator<Movimentacao> iterator = movimentacoes.iterator(); iterator.hasNext();) {
			Movimentacao movimentacao = (Movimentacao) iterator.next();
			somaSaidas = somaSaidas.add(movimentacao.getValor());
		}
		RelatorioGastoDto relatorio = new RelatorioGastoDto();
		relatorio.setMovimentacoes(movimentacoes);
		relatorio.setValorTotalSaidas(somaSaidas);
		relatorio.setDataInicio(data);
		relatorio.setDataFim(data);
		return relatorio;
	}

}
