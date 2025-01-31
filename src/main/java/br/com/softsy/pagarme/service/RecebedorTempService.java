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
	public RecebedorTemp inserirRecebedorTemp(RecebedorTempDTO dto) {

		Character transfAutomatica = (dto.getTransfAutomatica() != null) ? dto.getTransfAutomatica() : 'S';
		Character transfIntervalo = (dto.getTransfIntervalo() != null) ? dto.getTransfIntervalo() : 'M';
		Integer transfDia = (dto.getTransfDia() != null) ? dto.getTransfDia() : 0;

		repository.inserirRecebedorTemp(dto.getIdConta(), dto.getIdUsuario(), dto.getTipoPessoa(), dto.getNome(),
				dto.getDocumento(), dto.getEmail(), transfAutomatica, transfIntervalo, transfDia, dto.getAntecipAut());

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
		transferencias.put("automatica", recebedor.getTransfAutomatica());
		transferencias.put("intervalo", recebedor.getTransfIntervalo());
		transferencias.put("dia", recebedor.getTransfDia());
		map.put("transferencias", transferencias);

		Map<String, Object> antecipacao = new LinkedHashMap<>();
		antecipacao.put("autorizada", recebedor.getAntecipAut());
		antecipacao.put("tipo", recebedor.getAntecipTp());
		antecipacao.put("volume", recebedor.getAntecipVolume());
		antecipacao.put("dias", recebedor.getAntecipDias());
		antecipacao.put("delay", recebedor.getAntecipDelay());
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
}
