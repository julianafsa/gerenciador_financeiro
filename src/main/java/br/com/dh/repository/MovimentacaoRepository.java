package br.com.dh.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.dh.enumerations.TipoMovimentacao;
import br.com.dh.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
	
	String query = "SELECT m FROM Movimentacao m";
	@Query(value = query, nativeQuery = false)
    List<Movimentacao> buscarBalanco();
	
	String queryBalanco = "SELECT m FROM Movimentacao m where m.tipo = ?1";
	@Query(value = queryBalanco, nativeQuery = false)
    List<Movimentacao> buscarBalanco(@Param("tipo") TipoMovimentacao tipo);
	
	String queryEntradas = "SELECT m FROM Movimentacao m where m.tipo = 1";
	@Query(value = queryEntradas, nativeQuery = false)
    List<Movimentacao> buscarBalancoEntradas();
	
	String querySaidas = "SELECT m FROM Movimentacao m where m.tipo = 0";
	@Query(value = querySaidas, nativeQuery = false)
    List<Movimentacao> buscarBalancoSaidas();
	
	//String relatorioGastoDia = "SELECT m FROM Movimentacao m where m.tipo = 0 and m.data_criacao = ?1";
	String relatorioGastoDia = "SELECT * FROM Movimentacao WHERE tipo = 0 and data_criacao=:data_criacao";
	@Query(value = relatorioGastoDia, nativeQuery = true)
    List<Movimentacao> relatorioGastoDia(@Param("data_criacao") LocalDate data_criacao);
	
	String relatorioGastoDiaCategoria = "SELECT * FROM Movimentacao WHERE "
			+ "tipo = 0 "
			+ "and data_criacao=:data_criacao "
			+ "and categoria_fk=:id_categoria";
	@Query(value = relatorioGastoDiaCategoria, nativeQuery = true)
    List<Movimentacao> relatorioGastoDiaCategoria(@Param("data_criacao") LocalDate data_criacao, @Param("id_categoria") String id_categoria);
	
	String relatorioGastoPeriodo = "SELECT * FROM Movimentacao WHERE tipo = 0 "
			+ "and (data_criacao)>=:data_inicio and (data_criacao)<=:data_fim";
	@Query(value = relatorioGastoPeriodo, nativeQuery = true)
    List<Movimentacao> relatorioGastoPeriodo(@Param("data_inicio") LocalDate data_inicio, @Param("data_fim") LocalDate data_fim);
	
	String relatorioGastoPeriodoCategoria = "SELECT * FROM Movimentacao WHERE tipo = 0 "
			+ "and (data_criacao)>=:data_inicio and (data_criacao)<=:data_fim "
			+ "and categoria_fk=:id_categoria";
	@Query(value = relatorioGastoPeriodoCategoria, nativeQuery = true)
    List<Movimentacao> relatorioGastoPeriodoCategoria(@Param("data_inicio") LocalDate data_inicio, @Param("data_fim") LocalDate data_fim, @Param("id_categoria") String id_categoria);
	
}
