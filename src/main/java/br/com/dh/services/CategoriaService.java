package br.com.dh.services;

import java.util.List;
import java.util.Optional;

import br.com.dh.model.Categoria;

public interface CategoriaService {

	List<Categoria> buscarTodos();
	Optional<Categoria> buscarPorId(Long id);
	Optional<Categoria> buscarPorNome(String nome);
	void editar(Categoria categoria);
	void excluir(Long id);
	Categoria salvar(Categoria categoria);

}
