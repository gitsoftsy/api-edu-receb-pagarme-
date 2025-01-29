package br.com.softsy.pagarme.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.dto.TipoEmpresaDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.TipoEmpresa;
import br.com.softsy.pagarme.repository.TipoEmpresaRepository;

@Service
public class TipoEmpresaService {

	@Autowired
	private TipoEmpresaRepository repository;

	public List<TipoEmpresaDTO> listarTudo() {
		return repository.findAll().stream().map(TipoEmpresaDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TipoEmpresaDTO buscarPorId(Long id) {
		TipoEmpresa tipoempresa = repository.getReferenceById(id);
		return new TipoEmpresaDTO(tipoempresa);
	}
}
