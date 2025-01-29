package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

	List<Conta> findByConta (String conta);
	
}
