package br.com.softsy.pagarme.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.dto.OcupacaoDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.Ocupacao;
import br.com.softsy.pagarme.repository.OcupacaoRepository;

@Service
public class OcupacaoService {

	@Autowired
	private OcupacaoRepository repository;

	public List<OcupacaoDTO> listarTudo() {
		return repository.findAll().stream().map(OcupacaoDTO::new).collect(Collectors.toList());
	}

}
