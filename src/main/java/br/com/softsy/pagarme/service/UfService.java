package br.com.softsy.pagarme.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.dto.UfDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.Uf;
import br.com.softsy.pagarme.repository.UfRepository;

@Service
public class UfService {

	@Autowired
	private UfRepository repository;

	public List<UfDTO> listarTudo() {
		return repository.findAll().stream().map(UfDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public UfDTO buscarPorId(Long id) {
		Uf uf = repository.getReferenceById(id);
		return new UfDTO(uf);
	}

	
	@Transactional
	public UfDTO salvar(UfDTO dto) {
		validarUf(dto.getNomeUf());

		Uf uf = criarUfAPartirDTO(dto);
		uf = repository.save(uf);

		return new UfDTO(uf);
	}

	@Transactional
	public UfDTO atualizar(UfDTO dto) {
		Uf uf = repository.getReferenceById(dto.getIdUf());
		atualizarDados(uf, dto);
		return new UfDTO(uf);
	}

	@Transactional
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	private Uf criarUfAPartirDTO(UfDTO dto) {
		Uf uf = new Uf();
		uf.setCodUf(dto.getCodUf());
		uf.setNomeUf(dto.getNomeUf());
		return uf;
	}

	private void validarUf(String codUf) {
		Optional<Uf> ufExistente = repository.findByCodUf(codUf).stream().findFirst();
		if (ufExistente.isPresent()) {
			throw new UniqueException("Essa UF já existe.");
		}
	}

	private void atualizarDados(Uf destino, UfDTO origem) {
		destino.setCodUf(origem.getCodUf());
		destino.setNomeUf(origem.getNomeUf());
	}
}
