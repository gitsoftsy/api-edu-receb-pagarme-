package br.com.softsy.pagarme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.OcupacaoRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRespLegalRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.CnpjResponse;

@Service
public class PagarmeRecebedorPjService {

	@Autowired
	private PagarmeRecebedorRepository recebedorRepository;

	@Autowired
	private PagarmeRecebedorPjRepository recebedorPjRepository;

	@Autowired
	private PagarmeRecebedorPjRespLegalRepository recebedorRespLegalRepository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private BancoRepository bancoRepository;

	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	public CnpjResponse verificarCnpj(String cnpj, Long idConta) {

		if (!contaRepository.existsById(idConta)) {
			return new CnpjResponse(false, null, null, "Conta inválida ou inexistente.");
		}

		boolean contaExisteEmRecebedorTemp = recebedorTempRepository.existsByConta_IdConta(idConta);
		boolean contaExisteEmPagarmeRecebedorPj = recebedorPjRepository.existsByConta_IdConta(idConta);

		if (!contaExisteEmRecebedorTemp && !contaExisteEmPagarmeRecebedorPj) {
			return new CnpjResponse(false, null, null, "ID da conta não encontrado em nenhuma tabela de recebedores.");
		}

		return recebedorTempRepository.findByDocumento(cnpj)
				.map(recebedorTemp -> new CnpjResponse(true, recebedorTemp.getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
						"Dados encontrados"))
				.orElseGet(() -> recebedorPjRepository.findByCnpj(cnpj)
						.map(pagarmeRecebedorPj -> new CnpjResponse(true, pagarmeRecebedorPj.getIdPagarmeRecebedorPj(),
								"TBL_PAGARME_RECEBEDOR_PJ", "Dados encontrados"))
				.orElse(new CnpjResponse(false, null, null, "CNPJ não encontrado em nenhuma tabela.")));
	}
}
