package br.com.softsy.pagarme.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.OcupacaoRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRepository;

import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.repository.TipoEmpresaRepository;

import br.com.softsy.pagarme.response.CnpjResponse;
import br.com.softsy.pagarme.dto.CadastroPagarmeRecebedorPjDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;

@Service
public class PagarmeRecebedorPjService {

	@Autowired
	private PagarmeRecebedorPjRepository recebedorPjRepository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private TipoEmpresaRepository tipoEmpresaRepository;

	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	@Autowired
	private BancoRepository bancoRepository;

	public CnpjResponse verificarCnpj(String cnpj, Long idConta) {

		if (!contaRepository.existsById(idConta)) {
			throw new IllegalArgumentException("Conta inválida ou inexistente.");
		}

		boolean contaExisteEmRecebedorTemp = recebedorTempRepository.existsByConta_IdConta(idConta);
		boolean contaExisteEmPagarmeRecebedorPj = recebedorPjRepository.existsByConta_IdConta(idConta);

		if (!contaExisteEmRecebedorTemp && !contaExisteEmPagarmeRecebedorPj) {
			throw new IllegalArgumentException("ID da conta não encontrado em nenhuma tabela de recebedores.");
		}

		if (cnpj == null || cnpj.trim().length() != 14 || !cnpj.matches("\\d{14}")) {
			throw new IllegalArgumentException("CNPJ inválido. O CNPJ deve conter exatamente 14 dígitos numéricos.");
		}

		return recebedorTempRepository.findByDocumento(cnpj)
				.map(recebedorTemp -> new CnpjResponse(true, recebedorTemp.getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
						"Dados encontrados"))
				.orElseGet(() -> recebedorPjRepository.findByCnpj(cnpj)
						.map(pagarmeRecebedorPj -> new CnpjResponse(true, pagarmeRecebedorPj.getIdPagarmeRecebedorPj(),
								"TBL_PAGARME_RECEBEDOR_PJ", "Dados encontrados"))
						.orElseThrow(() -> new IllegalArgumentException("CNPJ não encontrado em nenhuma tabela.")));
	}

	private void validarIdsExistentes(CadastroPagarmeRecebedorPjDTO cadastroDTO) {
		if (!recebedorTempRepository.existsById(cadastroDTO.getIdRecebedorTemp())) {
			throw new UniqueException("O ID do Recebedor Temporário não existe.");
		}
		if (!tipoEmpresaRepository.existsById(cadastroDTO.getIdTipoEmpresa())) {
			throw new UniqueException("O ID do Tipo de Empresa não existe.");
		}
		if (!ocupacaoRepository.existsById(cadastroDTO.getIdOcupacao())) {
			throw new UniqueException("O ID da Ocupação não existe.");
		}
		if (!bancoRepository.existsById(cadastroDTO.getIdBanco())) {
			throw new UniqueException("O ID do Banco não existe.");
		}
	}

	private void executarProcedureInsercaoRecebedorPj(CadastroPagarmeRecebedorPjDTO cadastroRecebedorPjDTO) {
		try {
			recebedorPjRepository.inserirRecebedorPJ(cadastroRecebedorPjDTO.getIdRecebedorTemp(),
					cadastroRecebedorPjDTO.getTelefone(), cadastroRecebedorPjDTO.getCelular(),
					cadastroRecebedorPjDTO.getNomeFantasia(), cadastroRecebedorPjDTO.getRazaoSocial(),
					cadastroRecebedorPjDTO.getSite(), cadastroRecebedorPjDTO.getIdTipoEmpresa(),
					cadastroRecebedorPjDTO.getDataFundacao().toString(),
					cadastroRecebedorPjDTO.getReceitaAnual() != null
							? cadastroRecebedorPjDTO.getReceitaAnual().doubleValue()
							: null,
					cadastroRecebedorPjDTO.getIdBanco(), cadastroRecebedorPjDTO.getAgencia(),
					cadastroRecebedorPjDTO.getDvAgencia(), cadastroRecebedorPjDTO.getContaBancaria(),
					cadastroRecebedorPjDTO.getDvConta(), cadastroRecebedorPjDTO.getEndereco(),
					cadastroRecebedorPjDTO.getNumero(), cadastroRecebedorPjDTO.getComplemento(),
					cadastroRecebedorPjDTO.getBairro(), cadastroRecebedorPjDTO.getCidade(),
					cadastroRecebedorPjDTO.getEstado(), cadastroRecebedorPjDTO.getCep(),
					cadastroRecebedorPjDTO.getPontoReferencia(), cadastroRecebedorPjDTO.getNomeRespLegal(),
					cadastroRecebedorPjDTO.getEmailRespLegal(), cadastroRecebedorPjDTO.getCpfRespLegal(),
					cadastroRecebedorPjDTO.getDataNascimentoRespLegal().toString(),
					cadastroRecebedorPjDTO.getNomeMaeRespLegal(), cadastroRecebedorPjDTO.getIdOcupacao(),
					cadastroRecebedorPjDTO.getRendaMensal().doubleValue(),
					cadastroRecebedorPjDTO.getEnderecoRespLegal(), cadastroRecebedorPjDTO.getNumeroRespLegal(),
					cadastroRecebedorPjDTO.getComplementoRespLegal(), cadastroRecebedorPjDTO.getBairroRespLegal(),
					cadastroRecebedorPjDTO.getCidadeRespLegal(), cadastroRecebedorPjDTO.getEstadoRespLegal(),
					cadastroRecebedorPjDTO.getCepRespLegal(), cadastroRecebedorPjDTO.getPontoReferenciaRespLegal(),
					cadastroRecebedorPjDTO.getTelefoneRespLegal(), cadastroRecebedorPjDTO.getCelularRespLegal());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public PagarmeRecebedorPj inserirRecebedorPJ(Long idRecebedorTemp, Long headerIdConta,
			CadastroPagarmeRecebedorPjDTO cadastroDTO) {

		if (idRecebedorTemp == null) {
			throw new IllegalArgumentException("O ID do Recebedor Temporário não pode ser nulo.");
		}

		validarIdsExistentes(cadastroDTO);

		RecebedorTemp recebedorTemp = recebedorTempRepository.findById(idRecebedorTemp)
				.orElseThrow(() -> new IllegalArgumentException("Registro temporário não encontrado."));

		Long idContaFromTemp = recebedorTemp.getConta().getIdConta();

		if (!headerIdConta.equals(idContaFromTemp)) {
			throw new IllegalArgumentException(
					"O idConta informado no header não coincide com o registrado no Recebedor Temporário.");
		}

		String cnpj = recebedorTempRepository.findCnpjByRecebedorTempId(idRecebedorTemp).orElseThrow(
				() -> new IllegalArgumentException("Não foi possível encontrar o CNPJ do Recebedor Temporário."));

		if (recebedorPjRepository.existsByCnpj(cnpj)) {
			throw new IllegalArgumentException("Já existe um recebedor PJ com o CNPJ informado.");
		}

		executarProcedureInsercaoRecebedorPj(cadastroDTO);

		return recebedorPjRepository.findByCnpj(cnpj).orElseThrow(
				() -> new IllegalArgumentException("Erro ao buscar o recebedor PJ recém inserido pelo CNPJ."));
	}

	public Map<String, Object> formatarRetorno(PagarmeRecebedorPj recebedor) {

		Map<String, Object> respostaFormatada = new LinkedHashMap<>();

		respostaFormatada.put("idConta", recebedor.getConta() != null ? recebedor.getConta().getIdConta() : null);
		respostaFormatada.put("idUsuario",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getIdPagarmeRecebedor()
						: null);
		respostaFormatada.put("tipoPessoa", "JURIDICA");
		respostaFormatada.put("email",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getEmail() : null);
		respostaFormatada.put("transfIntervalo", "DIARIO");
		respostaFormatada.put("antecipAut",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getAntecipAut() : false);
		respostaFormatada.put("telefone",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getTelefone() : null);
		respostaFormatada.put("celular",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getCelular() : null);
		respostaFormatada.put("cnpj", recebedor.getCnpj());
		respostaFormatada.put("nomeFantasia", recebedor.getNomeFantasia());
		respostaFormatada.put("razaoSocial", recebedor.getRazaoSocial());
		respostaFormatada.put("site", recebedor.getSite());
		respostaFormatada.put("idTipoEmpresa",
				recebedor.getTipoEmpresa() != null ? recebedor.getTipoEmpresa().getIdTipoEmpresa() : null);
		respostaFormatada.put("dtFundacao", recebedor.getDataFundacao());
		respostaFormatada.put("receitaAnual", recebedor.getReceitaAnual());
		respostaFormatada.put("idBanco", recebedor.getBanco() != null ? recebedor.getBanco().getIdBanco() : null);
		respostaFormatada.put("agencia", recebedor.getAgencia());
		respostaFormatada.put("dvAgencia", recebedor.getDvAgencia());
		respostaFormatada.put("conta", recebedor.getContaBancaria());
		respostaFormatada.put("dvConta", recebedor.getDvConta());

		// endereço da empresa
		Map<String, Object> enderecoEmpresa = new LinkedHashMap<>();
		enderecoEmpresa.put("endereco", recebedor.getEndereco());
		enderecoEmpresa.put("numero", recebedor.getNumero());
		enderecoEmpresa.put("complemento", recebedor.getComplemento());
		enderecoEmpresa.put("bairro", recebedor.getBairro());
		enderecoEmpresa.put("cidade", recebedor.getCidade());
		enderecoEmpresa.put("estado", recebedor.getEstado());
		enderecoEmpresa.put("cep", recebedor.getCep());
		enderecoEmpresa.put("pontoReferencia", recebedor.getPontoReferencia());

		respostaFormatada.put("enderecoEmpresa", enderecoEmpresa);

		// responsável legal
		if (recebedor.getPagarmeRecebedor() != null
				&& recebedor.getPagarmeRecebedor().getPagarmeRecebedorPjRespLegal() != null) {
			PagarmeRecebedorPjRespLegal respLegal = recebedor.getPagarmeRecebedor().getPagarmeRecebedorPjRespLegal();

			Map<String, Object> enderecoRespLegal = new LinkedHashMap<>();
			enderecoRespLegal.put("endereco", respLegal.getEnderecoRespLegal());
			enderecoRespLegal.put("numero", respLegal.getNumeroRespLegal());
			enderecoRespLegal.put("complemento", respLegal.getComplementoRespLegal());
			enderecoRespLegal.put("bairro", respLegal.getBairroRespLegal());
			enderecoRespLegal.put("cidade", respLegal.getCidadeRespLegal());
			enderecoRespLegal.put("estado", respLegal.getEstadoRespLegal());
			enderecoRespLegal.put("cep", respLegal.getCepRespLegal());
			enderecoRespLegal.put("pontoReferencia", respLegal.getPontoReferenciaRespLegal());

			Map<String, Object> respLegalMap = new LinkedHashMap<>();
			respLegalMap.put("nome", respLegal.getNomeRespLegal());
			respLegalMap.put("email", respLegal.getEmailRespLegal());
			respLegalMap.put("cpf", respLegal.getCpfRespLegal());
			respLegalMap.put("dtNasc", respLegal.getDtNascRespLegal());
			respLegalMap.put("nomeMae", respLegal.getNomeMaeRespLegal());
			respLegalMap.put("idOcupacao",
					respLegal.getOcupacao() != null ? respLegal.getOcupacao().getIdOcupacao() : null);
			respLegalMap.put("rendaMensal", respLegal.getRendaMensal());
			respLegalMap.put("endereco", enderecoRespLegal);
			respLegalMap.put("telefone", respLegal.getTelefoneRespLegal());
			respLegalMap.put("celular", respLegal.getCelularRespLegal());

			respostaFormatada.put("respLegal", respLegalMap);
		}

		return respostaFormatada;
	}

}
