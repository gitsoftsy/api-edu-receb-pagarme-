package br.com.softsy.pagarme.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.infra.exception.UniqueException;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.EmailResponse;
import br.com.softsy.pagarme.dto.PagarmeRecebedorDTO;

@Service
public class PagarmeRecebedorService {

	@Autowired
	private PagarmeRecebedorRepository repository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	public List<PagarmeRecebedor> listarTudo() {
		return repository.findAll();
	}

	public EmailResponse verificarEmail(String email, Long idConta) {

		boolean contaExiste = repository.existsById(idConta) || recebedorTempRepository.existsById(idConta);

		if (!contaExiste) {
			return new EmailResponse(false, null, null, "Conta inválida ou inexistente.");
		}

		Optional<RecebedorTemp> recebedorTemp = recebedorTempRepository.findByEmail(email);
		if (recebedorTemp.isPresent()) {
			return new EmailResponse(true, recebedorTemp.get().getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
					"Dados encontrados");
		}

		Optional<PagarmeRecebedor> pagarmeRecebedor = repository.findByEmail(email);
		if (pagarmeRecebedor.isPresent()) {
			return new EmailResponse(true, pagarmeRecebedor.get().getIdPagarmeRecebedor(), "TBL_PAGARME_RECEBEDOR",
					"Dados encontrados");
		}

		return new EmailResponse(false, null, null, null);
	}

}
