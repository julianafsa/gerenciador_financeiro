package br.com.dh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.model.Categoria;
import br.com.dh.model.dto.CategoriaDto;
import br.com.dh.services.CategoriaService;
//import io.swagger.annotations.Api;

//@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService service;

    @Autowired
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> listar() {
    	List<Categoria> categorias = service.getAll();
        return ResponseEntity.ok(CategoriaDto.converter(categorias));
    }
    
}
