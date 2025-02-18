package br.com.softsy.pagarme.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.softsy.pagarme.infra.config.PasswordEncrypt;
import br.com.softsy.pagarme.infra.exception.UniqueException;

import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPfRepository;
import br.com.softsy.pagarme.dto.CadastroRecebedorTempDTO;

@Service
public class RecebedorTempService {

	@Autowired
	private RecebedorTempRepository repository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PagarmeRecebedorRepository pagarmeRecebedorRepository;

	@Autowired
	private PagarmeRecebedorPjRepository pagarmeRecebedorPJRepository;

	@Autowired
	private PagarmeRecebedorPfRepository pagarmeRecebedorPFRepository;

	@Autowired
	private PasswordEncrypt passwordEncrypt;

	public List<RecebedorTemp> listarTudo() {
		return repository.findAll();
	}

	@Transactional
	public RecebedorTemp inserirRecebedorTemp(CadastroRecebedorTempDTO cadastroRecebedorTempDTO) {

		if (pagarmeRecebedorRepository.existsByEmail(cadastroRecebedorTempDTO.getEmail())) {
			throw new UniqueException("Já existe um recebedor na base com este e-mail cadastrado.");
		}

		if (pagarmeRecebedorPJRepository.existsByCnpj(cadastroRecebedorTempDTO.getDocumento())) {
			throw new UniqueException("Já existe um recebedor na base com este documento cadastrado.");
		}

		if (pagarmeRecebedorPFRepository.existsByCpf(cadastroRecebedorTempDTO.getDocumento())) {
			throw new UniqueException("Já existe um recebedor na base com este CPF cadastrado.");
		}

		String documento = cadastroRecebedorTempDTO.getDocumento();
		if ("PF".equalsIgnoreCase(cadastroRecebedorTempDTO.getTipoPessoa()) && documento.length() != 11) {
			throw new IllegalArgumentException("CPF inválido! O CPF deve conter exatamente 11 caracteres.");
		}

		if ("PJ".equalsIgnoreCase(cadastroRecebedorTempDTO.getTipoPessoa()) && documento.length() != 14) {
			throw new IllegalArgumentException("CNPJ inválido! O CNPJ deve conter exatamente 14 caracteres.");
		}

		if (repository.findByDocumento(documento).isPresent()) {
			throw new UniqueException("Já existe um recebedor temporário com este documento cadastrado.");
		}

		if (repository.existsByEmail(cadastroRecebedorTempDTO.getEmail())) {
			throw new UniqueException("Já existe um recebedor temporário com este e-mail cadastrado.");
		}

		String baseSenha = documento.substring(0, Math.min(5, documento.length()));
		String senhaCriptografada = passwordEncrypt.hashPassword(baseSenha);

		Character transfAutomatica = (cadastroRecebedorTempDTO.getTransfAutomatica() != null)
				? cadastroRecebedorTempDTO.getTransfAutomatica()
				: 'S';
		Character transfIntervalo = (cadastroRecebedorTempDTO.getTransfIntervalo() != null)
				? cadastroRecebedorTempDTO.getTransfIntervalo()
				: 'M';
		Integer transfDia = (cadastroRecebedorTempDTO.getTransfDia() != null) ? cadastroRecebedorTempDTO.getTransfDia()
				: 0;

		repository.inserirRecebedorTemp(cadastroRecebedorTempDTO.getIdConta(), cadastroRecebedorTempDTO.getIdUsuario(),
				cadastroRecebedorTempDTO.getTipoPessoa(), cadastroRecebedorTempDTO.getNome(), documento,
				cadastroRecebedorTempDTO.getEmail(), senhaCriptografada, transfAutomatica, transfIntervalo, transfDia,
				cadastroRecebedorTempDTO.getAntecipAut());

		return repository.findTopByOrderByIdRecebedorTempDesc();
	}

	public Map<String, Object> formatarRecebedorTemp(RecebedorTemp recebedor) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("idRecebedorTemp", recebedor.getIdRecebedorTemp());
		map.put("nome", recebedor.getNome());
		map.put("documento", recebedor.getDocumento());
		map.put("email", recebedor.getEmail());
		map.put("tipoPessoa", recebedor.getTipoPessoa());
		map.put("dataCadastro", recebedor.getDataCadastro());

		Map<String, Object> transferencias = new LinkedHashMap<>();
		transferencias.put("transf_automatica", recebedor.getTransfAutomatica());
		transferencias.put("transf_intervalo", recebedor.getTransfIntervalo());
		transferencias.put("transf_dia", recebedor.getTransfDia());
		map.put("transferencias", transferencias);

		Map<String, Object> antecipacao = new LinkedHashMap<>();
		antecipacao.put("antecip_autorizada", recebedor.getAntecipAut());
		antecipacao.put("antecip_tipo", recebedor.getAntecipTp());
		antecipacao.put("antecip_volume", recebedor.getAntecipVolume());
		antecipacao.put("antecip_dias", recebedor.getAntecipDias());
		antecipacao.put("antecip_delay", recebedor.getAntecipDelay());
		map.put("antecipacao", antecipacao);

		if (recebedor.getConta() != null) {
			Map<String, Object> conta = new LinkedHashMap<>();
			conta.put("id", recebedor.getConta().getIdConta());
			conta.put("nome", recebedor.getConta().getConta());
			conta.put("tipo", recebedor.getConta().getTipoConta());
			conta.put("cnpj", recebedor.getConta().getCnpj());
			conta.put("uf", recebedor.getConta().getUf());
			map.put("conta", conta);
		}

		if (recebedor.getUsuario() != null) {
			Map<String, Object> usuario = new LinkedHashMap<>();
			usuario.put("id", recebedor.getUsuario().getIdUsuario());
			usuario.put("nome", recebedor.getUsuario().getNomeCompleto());
			usuario.put("email", recebedor.getUsuario().getEmail());
			map.put("usuario", usuario);
		}

		return map;
	}

	public Map<String, Object> obterRecebedorTemp(Long idConta, Long idRecebedorTemp) {

		boolean contaExiste = contaRepository.existsById(idConta);
		if (!contaExiste) {
			throw new IllegalArgumentException("Conta inválida ou inexistente.");
		}

		RecebedorTemp recebedor = repository.findById(idRecebedorTemp)
				.orElseThrow(() -> new IllegalArgumentException("Recebedor inválido ou inexistente."));

		return formatarRecebedorTemp(recebedor);
	}

}
