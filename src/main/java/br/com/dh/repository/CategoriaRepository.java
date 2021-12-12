package br.com.dh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.dh.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	String query = "SELECT c FROM Categoria c WHERE c.nome = ?1";
	@Query(value = query, nativeQuery = false)
	Optional<Categoria> buscarPorNome(String nome);
}
