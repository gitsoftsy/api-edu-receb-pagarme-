package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

	List<Banco> findByBanco(String banco);
}
