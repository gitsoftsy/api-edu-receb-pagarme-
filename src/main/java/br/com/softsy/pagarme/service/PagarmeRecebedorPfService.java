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

import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPfRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.CpfResponse;
import br.com.softsy.pagarme.response.EmailResponse;
import br.com.softsy.pagarme.dto.PagarmeRecebedorPfDTO;

@Service
public class PagarmeRecebedorPfService {

	@Autowired
	private PagarmeRecebedorPfRepository repository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	public List<PagarmeRecebedorPF> listarTodos() {
		return repository.findAll();
	}

	public CpfResponse verificarCpf(String cpf, Long idConta) {

		boolean contaExiste = contaRepository.existsById(idConta);
		if (!contaExiste) {
			return new CpfResponse(false, null, null, "Conta inválida ou inexistente.");
		}

		return recebedorTempRepository.findByDocumento(cpf).map(recebedorTemp -> new CpfResponse(true,
				recebedorTemp.getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP", "Dados encontrados")).orElseGet(() ->

		repository.findByCpf(cpf)
				.map(pagarmeRecebedorPf -> new CpfResponse(true, pagarmeRecebedorPf.getIdPagarmeRecebedorPF(),
						"TBL_PAGARME_RECEBEDOR_PF", "Dados encontrados"))
				.orElse(new CpfResponse(false, null, null, "CPF não encontrado em nenhuma tabela.")));
	}

}
