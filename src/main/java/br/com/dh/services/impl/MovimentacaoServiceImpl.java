package br.com.dh.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.dh.model.Movimentacao;
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

}
