package br.com.alura.spring.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {
	
	List<Cargo> findByDescricaoOrId(String descricao, Integer id);

}
