package br.com.softsy.pagarme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.softsy.pagarme.model.TipoEmpresa;

@Repository
public interface TipoEmpresaRepository extends JpaRepository<TipoEmpresa, Long> {

	List<TipoEmpresa> findByTipoEmpresa(String tipoEmpresa);
}
