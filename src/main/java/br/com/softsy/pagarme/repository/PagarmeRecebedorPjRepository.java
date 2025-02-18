package br.com.softsy.pagarme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.PagarmeRecebedorPj;

@Repository
public interface PagarmeRecebedorPjRepository extends  JpaRepository<PagarmeRecebedorPj, Long> {
	
	Optional<PagarmeRecebedorPj> findByCnpj(String cnpj);
	
	boolean existsById(Long idConta);
	
    boolean existsByConta_IdConta(Long idConta);
    
    //teste
    boolean existsByCnpj(String cnpj);


}
