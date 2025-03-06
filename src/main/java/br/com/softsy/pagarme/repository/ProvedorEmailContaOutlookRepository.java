package br.com.softsy.pagarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softsy.pagarme.model.ProvedorEmailContaOutlook;

public interface ProvedorEmailContaOutlookRepository extends JpaRepository<ProvedorEmailContaOutlook, Long> {
    ProvedorEmailContaOutlook findByIdConta(Long idConta);
}
