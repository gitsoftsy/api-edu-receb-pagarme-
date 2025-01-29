package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Long> {

	List<Uf> findByCodUf(String codUf);
}
