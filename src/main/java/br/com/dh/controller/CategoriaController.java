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
    
//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Contato>> getContactById(@PathVariable Integer id){
//        return ResponseEntity.ok(service.getContact(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<Contato> saveContact(@RequestBody @Valid Contato contato)  {
//		List<Contato> contatos = service.searchByContato(contato.getNome(), contato.getSobrenome(), 
//			contato.getDataNascimento(), contato.getApelido());
//		if (!contatos.isEmpty()) {
//			System.out.println("Cannot save duplicate contact.");
//			return ResponseEntity.badRequest().build();
//		}
//    	
//        Contato retorno = service.saveContact(contato);
//        URI uri = UriComponentsBuilder.fromPath("contato/{id}").buildAndExpand(contato.getId()).toUri();
//        return ResponseEntity.created(uri).body(retorno);
//    }
//    
//	@DeleteMapping("/{id}")
//	@Transactional
//	public ResponseEntity<?> remover(@PathVariable Integer id) {
//		Optional<Contato> optional = service.getContact(id);
//		if (optional.isPresent()) {
//			service.deleteById(id);
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
}
