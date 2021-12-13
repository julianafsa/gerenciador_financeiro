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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dh.model.Categoria;
import br.com.dh.model.Movimentacao;
import br.com.dh.model.dto.CategoriaDto;
import br.com.dh.model.dto.MovimentacaoDto;
import br.com.dh.services.MovimentacaoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

//@Api(tags = "Movimentação")
@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoService service;

    public MovimentacaoController(MovimentacaoService service) {
        this.service = service;
    }

    @GetMapping
    //@ApiOperation(value = "Lista todas as movimentações.")
    public ResponseEntity<List<MovimentacaoDto>> buscarTodos() {
    	List<Movimentacao> movimentacao = service.buscarTodos();
        return ResponseEntity.ok(MovimentacaoDto.converter(movimentacao));
    }
    
    @GetMapping("/{id}")
    //@ApiOperation(value = "Lista a movimentação buscada por id.")
    public ResponseEntity<MovimentacaoDto> buscarPorId(@PathVariable Long id){
    	Optional<Movimentacao> movimentacao = service.buscarPorId(id);
		if (movimentacao.isPresent()) {
			return ResponseEntity.ok(new MovimentacaoDto(movimentacao.get()));
		}
		return ResponseEntity.notFound().build();
    }
    
	@DeleteMapping("/{id}")
	@Transactional
    //@ApiOperation(value = "Exclui uma movimentação.")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Movimentacao> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			service.excluir(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
    
    /*@PostMapping
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
	}*/
    
}
