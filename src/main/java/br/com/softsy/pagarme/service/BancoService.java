package br.com.softsy.pagarme.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.Banco;
import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.dto.BancoDTO;

@Service
public class BancoService {

	@Autowired
	private BancoRepository repository;

	public List<BancoDTO> listarTudo() {
		return repository.findAll().stream().map(BancoDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public BancoDTO buscarPorId(Long id) {
		Banco banco = repository.getReferenceById(id);
		return new BancoDTO(banco);
	}
}
