package br.com.dh.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.dh.model.Categoria;
import br.com.dh.repository.CategoriaRepository;
import br.com.dh.services.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
    CategoriaRepository repository;
    
    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<Categoria> buscarTodos() {
        return this.repository.findAll();
	}

	@Override
	public Optional<Categoria> buscarPorId(Long id) {
		//return Optional.ofNullable(this.repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe categoria com o id " + id)));
		return this.repository.findById(id);
	}

	@Override
	public Optional<Categoria> buscarPorNome(String nome) {
		return this.repository.buscarPorNome(nome);
	}

	@Override
	public void editar(Categoria categoria) {
		Categoria categoriaVelha = this.repository.findById(categoria.getId()).get();
		String nome = categoriaVelha.getNome();
		BigDecimal limiteMensal = categoriaVelha.getLimiteMensal();
		if (nome != null) {
			categoriaVelha.setNome(nome);
		}
		if (limiteMensal != null) {
			categoriaVelha.setLimiteMensal(limiteMensal);
		}
		this.repository.save(categoriaVelha);
	}

	@Override
	public void excluir(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public Categoria salvar(Categoria categoria) {
		return this.repository.save(categoria);
	}

}
