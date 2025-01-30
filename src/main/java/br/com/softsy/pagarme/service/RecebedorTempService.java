package br.com.softsy.pagarme.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.infra.exception.UniqueException;

import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.dto.RecebedorTempDTO;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;

@Service
public class RecebedorTempService {

	@Autowired
	private RecebedorTempRepository repository;

	public List<RecebedorTemp> listarTudo() {
		return repository.findAll();
	}

	@Transactional
	public void inserirRecebedorTemp(RecebedorTempDTO dto) {
		// aplicar valores padrão caso os campos venham nulos
		Character transfAutomatica = (dto.getTransfAutomatica() != null) ? dto.getTransfAutomatica() : 'S';
		Character transfIntervalo = (dto.getTransfIntervalo() != null) ? dto.getTransfIntervalo() : 'M';
		Integer transfDia = (dto.getTransfDia() != null) ? dto.getTransfDia() : 0;

		// chamada ao repository garantindo valores corretos (padroes) do banco, S,M e 0
		repository.inserirRecebedorTemp(dto.getIdConta(), dto.getIdUsuario(), dto.getTipoPessoa(), dto.getNome(),
				dto.getDocumento(), dto.getEmail(), transfAutomatica, transfIntervalo, transfDia, dto.getAntecipAut());
	}

}