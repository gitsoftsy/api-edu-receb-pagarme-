package br.com.softsy.pagarme.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;
import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.OcupacaoRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRespLegalRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.CnpjResponse;
import br.com.softsy.pagarme.dto.CadastroPagarmeRecebedorPjDTO;

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

	private void executarProcedureInsercaoRecebedorPj(CadastroPagarmeRecebedorPjDTO cadastroRecebedorPjDTO) {
		try {
			System.out.printf("📌 Executando Procedure...\n");
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
			System.out.printf("✅ Procedure executada com sucesso!\n");
		} catch (Exception e) {
			System.out.printf("❌ Erro ao executar a procedure: %s\n", e.getMessage());
			throw new RuntimeException("Erro ao inserir o Recebedor PJ no banco de dados", e);
		}
	}

	@Transactional
	public PagarmeRecebedorPj inserirRecebedorPJ(Long idRecebedorTemp,
			CadastroPagarmeRecebedorPjDTO cadastroRecebedorPjDTO) {

		if (idRecebedorTemp == null) {
			throw new IllegalArgumentException(" O ID do Recebedor Temporário não pode ser nulo.");
		}

		String cnpj = recebedorTempRepository.findCnpjByRecebedorTempId(idRecebedorTemp).orElseThrow(
				() -> new IllegalArgumentException(" Não foi possível encontrar o CNPJ do Recebedor Temporário."));

		executarProcedureInsercaoRecebedorPj(cadastroRecebedorPjDTO);

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
		respostaFormatada.put("senha", "senhaSegura123");
		respostaFormatada.put("transfIntervalo", "DIARIO");
		respostaFormatada.put("antecipAut", true);
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

		// Endereço da empresa
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

		// Responsável legal
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
