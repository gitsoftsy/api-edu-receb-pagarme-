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
import br.com.softsy.pagarme.dto.RecebedorTempDTO;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;

@Service
public class RecebedorTempService {

	@Autowired
	private RecebedorTempRepository repository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private PasswordEncrypt passwordEncrypt; // Injeta a classe de criptografia

	public List<RecebedorTemp> listarTudo() {
		return repository.findAll();
	}

	@Transactional
	public RecebedorTemp inserirRecebedorTemp(RecebedorTempDTO recebedorTempDto) {

		if (repository.findByDocumento(recebedorTempDto.getDocumento()).isPresent()) {
			throw new UniqueException("Já existe um recebedor com este documento cadastrado.");
		}

		if (repository.existsByEmail(recebedorTempDto.getEmail())) {
			throw new UniqueException("Já existe um recebedor com este e-mail cadastrado.");
		}
		
        String baseSenha = recebedorTempDto.getDocumento().substring(0, Math.min(5, recebedorTempDto.getDocumento().length()));
        String senhaCriptografada = passwordEncrypt.hashPassword(baseSenha);

		Character transfAutomatica = (recebedorTempDto.getTransfAutomatica() != null)
				? recebedorTempDto.getTransfAutomatica()
				: 'S';
		Character transfIntervalo = (recebedorTempDto.getTransfIntervalo() != null)
				? recebedorTempDto.getTransfIntervalo()
				: 'M';
		Integer transfDia = (recebedorTempDto.getTransfDia() != null) ? recebedorTempDto.getTransfDia() : 0;

		
        repository.inserirRecebedorTemp(
                recebedorTempDto.getIdConta(), recebedorTempDto.getIdUsuario(),
                recebedorTempDto.getTipoPessoa(), recebedorTempDto.getNome(),
                recebedorTempDto.getDocumento(), recebedorTempDto.getEmail(),
                senhaCriptografada,  
                transfAutomatica, transfIntervalo, transfDia, recebedorTempDto.getAntecipAut()
        );

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
