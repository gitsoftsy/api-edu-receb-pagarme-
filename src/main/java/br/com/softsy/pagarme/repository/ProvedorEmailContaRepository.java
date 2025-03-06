package br.com.softsy.pagarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softsy.pagarme.model.ProvedorEmailConta;

public interface ProvedorEmailContaRepository extends JpaRepository<ProvedorEmailConta, Long> {
    ProvedorEmailConta findByIdContaAndIdProvedorEmail(Long idConta, Long provedorEmail);
}
