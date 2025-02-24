package br.com.softsy.pagarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;

@Repository
public interface PagarmeRecebedorPjRespLegalRepository extends JpaRepository<PagarmeRecebedorPjRespLegal, Long> {
	
}
