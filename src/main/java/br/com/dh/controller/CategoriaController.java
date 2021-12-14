package br.com.dh.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dh.model.Categoria;
import br.com.dh.model.dto.CategoriaDto;
import br.com.dh.services.CategoriaService;
//import io.swagger.annotations.Api;

//@Api(tags = "Categoria")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    //@ApiOperation(value = "Lista todas as categorias.")
    public ResponseEntity<List<CategoriaDto>> buscarTodos() {
    	List<Categoria> categorias = service.buscarTodos();
        return ResponseEntity.ok(CategoriaDto.converter(categorias));
    }
    
    @GetMapping("/{id}")
    //@ApiOperation(value = "Lista a categoria buscada por id.")
    public ResponseEntity<CategoriaDto> buscarPorId(@PathVariable Long id){
    	Optional<Categoria> categoria = service.buscarPorId(id);
		if (categoria.isPresent()) {
			return ResponseEntity.ok(new CategoriaDto(categoria.get()));
		}
		return ResponseEntity.notFound().build();
    }
    
	@DeleteMapping("/{id}")
	@Transactional
    //@ApiOperation(value = "Exclui uma categoria.")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Categoria> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			service.excluir(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
    
    @PostMapping
    //@ApiOperation(value = "Salva uma categoria.")
    public ResponseEntity<Categoria> salvar(@RequestBody @Valid CategoriaDto categoriaInput, UriComponentsBuilder uriBuilder)  {
    	Optional<Categoria> categoria = service.buscarPorNome(categoriaInput.getNome()); 
		if (categoria.isPresent()) {
			System.out.println("Categoria " + categoriaInput.getNome() + " já está cadastrada.");
			return ResponseEntity.badRequest().build();
		}
    	
		Categoria categoriaSalva = service.salvar(CategoriaDto.converter(categoriaInput));
		URI uri = UriComponentsBuilder.fromPath("categoria").buildAndExpand(categoriaSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(categoriaSalva);
    }
    
	@PutMapping("/{id}")
	@Transactional
    //@ApiOperation(value = "Altera uma categoria.")
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid CategoriaDto form) {
		Optional<Categoria> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			Categoria categoria = form.atualizar(id, service);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		return ResponseEntity.notFound().build();
	}
    
}
