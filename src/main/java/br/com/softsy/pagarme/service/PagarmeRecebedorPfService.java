package br.com.softsy.pagarme.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPfRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.CpfResponse;

import br.com.softsy.pagarme.dto.CadastroPagarmeRecebedorPfDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.OcupacaoRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPfRepository;

@Service
public class PagarmeRecebedorPfService {

	@Autowired
	private PagarmeRecebedorPfRepository repository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private OcupacaoRepository ocupacaoRepository;

	@Autowired
	private BancoRepository bancoRepository;

	public List<PagarmeRecebedorPF> listarTodos() {
		return repository.findAll();
	}

	public Map<String, Object> buscarRecebedorPfPorId(Long idRecebedorPf) {
		PagarmeRecebedorPF recebedor = repository.findById(idRecebedorPf)
				.orElseThrow(() -> new IllegalArgumentException());

		return formatarRetorno(recebedor);
	}

	public CpfResponse verificarCpf(String cpf, Long idConta) {

		if (!contaRepository.existsById(idConta)) {
			throw new IllegalArgumentException("Conta inválida ou inexistente.");
		}

		boolean contaExisteEmRecebedorTemp = recebedorTempRepository.existsByConta_IdConta(idConta);

		boolean contaExisteEmPagarmeRecebedorPf = repository.existsByEntidadeConta_IdConta(idConta);

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

	private void validarIdsExistentes(CadastroPagarmeRecebedorPfDTO cadastroDTO) {
		if (!recebedorTempRepository.existsById(cadastroDTO.getIdRecebedorTemp())) {
			throw new UniqueException("O ID do Recebedor Temporário não existe.");
		}
		if (!ocupacaoRepository.existsById(cadastroDTO.getIdOcupacao())) {
			throw new UniqueException("O ID da Ocupação não existe.");
		}
		if (!bancoRepository.existsById(cadastroDTO.getIdBanco())) {
			throw new UniqueException("O ID do Banco não existe.");
		}
	}

	private void executarProcedureInsercaoRecebedorPf(CadastroPagarmeRecebedorPfDTO cadastroRecebedorPfDTO) {
		try {
			repository.inserirRecebedorPF(cadastroRecebedorPfDTO.getIdRecebedorTemp(),
					cadastroRecebedorPfDTO.getTelefone(), cadastroRecebedorPfDTO.getCelular(),
					cadastroRecebedorPfDTO.getNomeDaMae(), cadastroRecebedorPfDTO.getDataNascimento().toString(),
					cadastroRecebedorPfDTO.getIdOcupacao(), cadastroRecebedorPfDTO.getRendaMensal().doubleValue(),
					cadastroRecebedorPfDTO.getIdBanco(), cadastroRecebedorPfDTO.getAgencia(),
					cadastroRecebedorPfDTO.getDvAgencia(), cadastroRecebedorPfDTO.getContaBancaria(),
					cadastroRecebedorPfDTO.getDvConta(), cadastroRecebedorPfDTO.getEndereco(),
					cadastroRecebedorPfDTO.getNumero(), cadastroRecebedorPfDTO.getComplemento(),
					cadastroRecebedorPfDTO.getBairro(), cadastroRecebedorPfDTO.getCidade(),
					cadastroRecebedorPfDTO.getEstado(), cadastroRecebedorPfDTO.getCep(),
					cadastroRecebedorPfDTO.getPontoReferencia());
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public PagarmeRecebedorPF inserirRecebedorPF(Long idRecebedorTemp, Long headerIdConta,
			CadastroPagarmeRecebedorPfDTO cadastroDTO) {

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

		String cpf = recebedorTemp.getDocumento();
		if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
			throw new IllegalArgumentException("CPF inválido. O CPF deve conter exatamente 11 dígitos numéricos.");
		}

		if (repository.existsByCpf(cpf)) {
			throw new IllegalArgumentException("Já existe um recebedor PF com o CPF informado.");
		}

		executarProcedureInsercaoRecebedorPf(cadastroDTO);

		return repository.findByCpf(cpf).orElseThrow(
				() -> new IllegalArgumentException("Erro ao buscar o recebedor PF recém inserido pelo CPF."));
	}

	public Map<String, Object> formatarRetorno(PagarmeRecebedorPF recebedor) {
		Map<String, Object> respostaFormatada = new LinkedHashMap<>();

		respostaFormatada.put("idConta",
				recebedor.getEntidadeConta() != null ? recebedor.getEntidadeConta().getIdConta() : null);
		respostaFormatada.put("idUsuario",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getIdPagarmeRecebedor()
						: null);
		respostaFormatada.put("tipoPessoa", "FISICA");
		respostaFormatada.put("email",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getEmail() : null);
		respostaFormatada.put("transfIntervalo", "DIARIO");
		respostaFormatada.put("antecipAut",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getAntecipAut() : false);

		respostaFormatada.put("telefone",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getTelefone() : null);
		respostaFormatada.put("celular",
				recebedor.getPagarmeRecebedor() != null ? recebedor.getPagarmeRecebedor().getCelular() : null);
		respostaFormatada.put("cpf", recebedor.getCpf());
		respostaFormatada.put("nomeCompleto", recebedor.getNomeCompleto());
		respostaFormatada.put("nomeMae", recebedor.getNomeDaMae());
		respostaFormatada.put("dtNascimento", recebedor.getDataNascimento());
		respostaFormatada.put("idOcupacao",
				recebedor.getOcupacao() != null ? recebedor.getOcupacao().getIdOcupacao() : null);
		respostaFormatada.put("rendaMensal", recebedor.getRendaMensal());
		respostaFormatada.put("idBanco", recebedor.getBanco() != null ? recebedor.getBanco().getIdBanco() : null);
		respostaFormatada.put("agencia", recebedor.getAgencia());
		respostaFormatada.put("dvAgencia", recebedor.getDvAgencia());
		respostaFormatada.put("contaBancaria", recebedor.getNumeroConta());
		respostaFormatada.put("dvConta", recebedor.getDvConta());

		Map<String, Object> endereco = new LinkedHashMap<>();
		endereco.put("endereco", recebedor.getEndereco());
		endereco.put("numero", recebedor.getNumero());
		endereco.put("complemento", recebedor.getComplemento());
		endereco.put("bairro", recebedor.getBairro());
		endereco.put("cidade", recebedor.getCidade());
		endereco.put("estado", recebedor.getEstado());
		endereco.put("cep", recebedor.getCep());
		endereco.put("pontoReferencia", recebedor.getPontoReferencia());

		respostaFormatada.put("endereco", endereco);

		return respostaFormatada;
	}

}
