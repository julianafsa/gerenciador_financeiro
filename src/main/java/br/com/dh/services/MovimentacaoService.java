package br.com.dh.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Movimentacao;
import br.com.dh.model.dto.BalancoDto;
import br.com.dh.model.dto.BalancoEntradasDto;
import br.com.dh.model.dto.BalancoSaidasDto;
import br.com.dh.model.dto.RelatorioGastoDto;

public interface MovimentacaoService {

	List<Movimentacao> buscarTodos();
	Optional<Movimentacao> buscarPorId(Long id);
	void editar(Movimentacao movimentacao);
	void excluir(Long id);
	Movimentacao salvar(Movimentacao movimentacao);
	
	BalancoDto buscarBalanco();
	BalancoDto buscarBalanco(TipoMovimentacao tipo);
	BalancoEntradasDto buscarBalancoEntradas();
	BalancoSaidasDto buscarBalancoSaidas();
	
	RelatorioGastoDto relatorioGastoDia(LocalDate data);
    RelatorioGastoDto relatorioGastoDiaCategoria(LocalDate data_criacao, String id_categoria);
	RelatorioGastoDto relatorioGastoPeriodo(LocalDate dataInicio, LocalDate dataFim);
	RelatorioGastoDto relatorioGastoPeriodoCategoria(LocalDate dataInicio, LocalDate dataFim, String id_categoria);

}
