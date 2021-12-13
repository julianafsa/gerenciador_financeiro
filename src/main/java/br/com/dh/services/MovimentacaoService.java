package br.com.dh.services;

import java.util.List;
import java.util.Optional;

import br.com.dh.model.Movimentacao;

public interface MovimentacaoService {

	List<Movimentacao> buscarTodos();
	Optional<Movimentacao> buscarPorId(Long id);
	void editar(Movimentacao movimentacao);
	void excluir(Long id);
	Movimentacao salvar(Movimentacao movimentacao);

}
