package br.com.dh.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.dh.config.validacao.MyException;
import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Categoria;
import br.com.dh.model.Movimentacao;
import br.com.dh.model.dto.BalancoDto;
import br.com.dh.model.dto.BalancoEntradasDto;
import br.com.dh.model.dto.BalancoSaidasDto;
import br.com.dh.model.dto.MovimentacaoDto;
import br.com.dh.model.dto.RelatorioGastoDto;
import br.com.dh.services.CategoriaService;
import br.com.dh.services.MovimentacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Movimentação")
@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private MovimentacaoService service;
    
    private CategoriaService categoriaService;

    public MovimentacaoController(MovimentacaoService service, CategoriaService categoriaService) {
        this.service = service;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as movimentações.")
    public ResponseEntity<List<MovimentacaoDto>> buscarTodos() {
    	List<Movimentacao> movimentacao = service.buscarTodos();
        return ResponseEntity.ok(MovimentacaoDto.converter(movimentacao));
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Lista a movimentação buscada por id.")
    public ResponseEntity<MovimentacaoDto> buscarPorId(@PathVariable Long id){
    	Optional<Movimentacao> movimentacao = service.buscarPorId(id);
		if (movimentacao.isPresent()) {
			return ResponseEntity.ok(new MovimentacaoDto(movimentacao.get()));
		}
		return ResponseEntity.notFound().build();
    }
    
	@DeleteMapping("/{id}")
	@Transactional
    @ApiOperation(value = "Exclui uma movimentação.")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Movimentacao> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			service.excluir(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
    
    @PostMapping
    @ApiOperation(value = "Salva uma movimentação se estiver dentro do limite mensal da categoria.")
    public ResponseEntity<Object> salvar(@RequestBody @Valid MovimentacaoDto input, UriComponentsBuilder uriBuilder) throws MyException  {
    	Optional<Categoria> categoria = categoriaService.buscarPorId(Long.parseLong(input.getIdCategoria())); 
		if (!categoria.isPresent()) {
			System.out.println("Categoria " + input.getIdCategoria() + " não encontrada.");
			return ResponseEntity.notFound().build();
		}
    	Movimentacao mov = input.converter(categoria.get());
		Movimentacao movimentacaoSalva = service.salvar(mov);
		if (movimentacaoSalva == null) {
			String body = "Não é possível adicionar essa movimentação. Irá ultrapassar o limite máximo  para essa categoria.";
			throw new MyException(mov, body);
		}
		URI uri = UriComponentsBuilder.fromPath("categoria").buildAndExpand(movimentacaoSalva.getId()).toUri();
		return ResponseEntity.created(uri).body(movimentacaoSalva);			
    }
    
    @PutMapping("/{id}")
	@Transactional
    @ApiOperation(value = "Altera uma movimentação.")
	public ResponseEntity<MovimentacaoDto> atualizar(@PathVariable Long id, @RequestBody @Valid MovimentacaoDto form) {
		Optional<Movimentacao> optional = service.buscarPorId(id);
		if (optional.isPresent()) {
			Movimentacao movimentacao = form.atualizar(id, service);
			return ResponseEntity.ok(new MovimentacaoDto(movimentacao));
		}
		return ResponseEntity.notFound().build();
	}
    
    ////////////////////// BALANCOS //////////////////////
    
    // Balanços
    @GetMapping("/balanco")
    @ApiOperation(value = "Lista todas as movimentações: entradas e saídas.")
    public ResponseEntity<BalancoDto> buscarBalanco() {
    	BalancoDto movimentacao = service.buscarBalanco();
        return ResponseEntity.ok(movimentacao);
    }
    
    // Balanços
    @GetMapping("/balanco/{tipo}")
    @ApiOperation(value = "Lista todas as entradas ou saídas. 0 para DESPESA e 1 para RECEITA")
    public ResponseEntity<BalancoDto> buscarBalanco(@PathVariable String tipo) {
    	TipoMovimentacao tipoInput = TipoMovimentacao.DESPESA;
    	if (tipo.equals("0")) {
    		tipoInput = TipoMovimentacao.DESPESA;
    	} else if (tipo.equals("1")) {
    		tipoInput = TipoMovimentacao.RECEITA;
    	}
    	BalancoDto balanco = service.buscarBalanco(tipoInput);
        return ResponseEntity.ok(balanco);
    }
    
    // Balanços
    @GetMapping("/balanco/entradas")
    @ApiOperation(value = "Lista todas as entradas.")
    public ResponseEntity<BalancoEntradasDto> buscarBalancoEntradas() {
    	BalancoEntradasDto balanco = service.buscarBalancoEntradas();
        return ResponseEntity.ok(balanco);
    }
    
    // Balanços
    @GetMapping("/balanco/saidas")
    @ApiOperation(value = "Lista todas as saídas.")
    public ResponseEntity<BalancoSaidasDto> buscarBalancoSaidas() {
    	BalancoSaidasDto balanco = service.buscarBalancoSaidas();
        return ResponseEntity.ok(balanco);
    }
    
    ////////////////////// RELATORIOS //////////////////////
    
    // Balanços
    @GetMapping("/gastos")
    @ApiOperation(value = "Relatorio de gastos por um determinado dia.")
    public ResponseEntity<RelatorioGastoDto> relatorioGastoDia(
    	//@RequestParam("localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) {
    	@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    	RelatorioGastoDto balanco = service.relatorioGastoDia(date);
        return ResponseEntity.ok(balanco);
    }
    
    // Balanços
    @GetMapping("/gastos/categoria")
    @ApiOperation(value = "Relatorio de gastos por um determinado dia e por categoria.")
    public ResponseEntity<RelatorioGastoDto> relatorioGastoDiaCategoria(
    	@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
    	@RequestParam("categoria") String categoria) {
    	RelatorioGastoDto balanco = service.relatorioGastoDiaCategoria(date, categoria);
        return ResponseEntity.ok(balanco);
    }
    
    // Balanços
    @GetMapping("/gastos/periodo")
    @ApiOperation(value = "Relatorio de gastos por um período (dia de início e de fim).")
    public ResponseEntity<RelatorioGastoDto> relatorioGastoPeriodo(
    	//@RequestParam("localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) {
    	@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
    	@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
    	RelatorioGastoDto balanco = service.relatorioGastoPeriodo(dataInicio, dataFim);
        return ResponseEntity.ok(balanco);
    }
    
    // Balanços
    @GetMapping("/gastos/periodo/categoria")
    @ApiOperation(value = "Relatorio de gastos por perídodo (dia de início e de fim) e por categoria.")
    public ResponseEntity<RelatorioGastoDto> relatorioGastoPeriodoCategoria(
    	//@RequestParam("localDate") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate localDate) {
    	@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
    	@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
    	@RequestParam("categoria") String categoria) {
    	RelatorioGastoDto balanco = service.relatorioGastoPeriodoCategoria(dataInicio, dataFim, categoria);
        return ResponseEntity.ok(balanco);
    }
    
}
