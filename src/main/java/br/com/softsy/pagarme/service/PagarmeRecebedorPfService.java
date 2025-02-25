package br.com.softsy.pagarme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPfRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.CpfResponse;

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

		if (!contaRepository.existsById(idConta)) {
			throw new IllegalArgumentException("Conta inválida ou inexistente.");
		}

		boolean contaExisteEmRecebedorTemp = recebedorTempRepository.existsByConta_IdConta(idConta);
		boolean contaExisteEmPagarmeRecebedorPf = repository.existsByConta_IdConta(idConta);

		if (!contaExisteEmRecebedorTemp && !contaExisteEmPagarmeRecebedorPf) {
			throw new IllegalArgumentException("ID da conta não encontrado em nenhuma tabela de recebedores.");
		}

		if (cpf == null || cpf.trim().length() != 11) {
			throw new IllegalArgumentException("CPF inválido. O CPF deve conter exatamente 11 dígitos.");
		}

		return recebedorTempRepository.findByDocumento(cpf)
				.map(recebedorTemp -> new CpfResponse(true, recebedorTemp.getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
						"Dados encontrados"))
				.orElseGet(() -> repository.findByCpf(cpf)
						.map(pagarmeRecebedorPf -> new CpfResponse(true, pagarmeRecebedorPf.getIdPagarmeRecebedorPF(),
								"TBL_PAGARME_RECEBEDOR_PF", "Dados encontrados"))
						.orElseThrow(() -> new IllegalArgumentException("CPF não encontrado em nenhuma tabela.")));
	}

}
