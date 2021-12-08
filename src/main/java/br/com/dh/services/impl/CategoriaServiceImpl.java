package br.com.dh.services.impl;

import java.util.List;

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
	public List<Categoria> getAll() {
        return repository.findAll();
	}

}
