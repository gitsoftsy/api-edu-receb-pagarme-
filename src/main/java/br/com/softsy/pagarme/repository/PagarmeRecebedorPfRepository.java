package br.com.softsy.pagarme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.model.RecebedorTemp;

@Repository
public interface PagarmeRecebedorPfRepository extends JpaRepository<PagarmeRecebedorPF, Long> {

	List<PagarmeRecebedorPF> findAll();
	
	boolean existsById(Long idConta);

	Optional<PagarmeRecebedorPF> findByCpf(String cpf);
	

}
