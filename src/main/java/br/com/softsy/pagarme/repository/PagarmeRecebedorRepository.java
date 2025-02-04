package br.com.softsy.pagarme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.PagarmeRecebedor;

@Repository
public interface PagarmeRecebedorRepository extends JpaRepository<PagarmeRecebedor, Long> {

	List<PagarmeRecebedor> findByTipoPessoa(String tipoPessoa);

	Optional<PagarmeRecebedor> findById(Long idPagarmeRecebedor);

	boolean existsById(Long idConta);

	Optional<PagarmeRecebedor> findByEmail(String email);

}
