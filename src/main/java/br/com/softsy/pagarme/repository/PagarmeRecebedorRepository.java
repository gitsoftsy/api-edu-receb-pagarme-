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

	Optional<PagarmeRecebedor> findByEmail(String email);
	
    @Procedure(name = "PROC_FILTRAR_RECEBEDORES")
    List<Object[]> filtrarRecebedores(
            @Param("P_ID_CONTA") Long idConta,
            @Param("P_DOCUMENTO") String documento,
            @Param("P_NOME") String nome
                     
    );
    
    PagarmeRecebedor findPagarmeRecebedorByEmailAndAtivo(String email, Character ativo);
    
    boolean existsByConta_IdConta(Long idConta);
    
    boolean existsByEmail(String email);
}
