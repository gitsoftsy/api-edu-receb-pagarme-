package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.Ocupacao;


@Repository
public interface OcupacaoRepository extends JpaRepository<Ocupacao, Long> {

	List<Ocupacao> findByCodCBO(String codCBO);
	
}
