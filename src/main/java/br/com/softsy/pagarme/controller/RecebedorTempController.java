package br.com.softsy.pagarme.controller;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softsy.pagarme.dto.RecebedorTempDTO;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.service.RecebedorTempService;

@RestController
@RequestMapping("/recebedorTemp")
public class RecebedorTempController {

	@Autowired
	private RecebedorTempService service;

	@GetMapping
	public ResponseEntity<Map<String, Object>> listar() {

		List<RecebedorTemp> lista = service.listarTudo();

		List<Map<String, Object>> respostaFormatada = lista.stream().map(recebedor -> {
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
		}).toList();

		Map<String, Object> respostaFinal = new LinkedHashMap<>();
		respostaFinal.put("mensagem", "Dados encontrados:");
		respostaFinal.put("quantidade", respostaFormatada.size());
		respostaFinal.put("dados", respostaFormatada);

		return ResponseEntity.ok(respostaFinal);
	}

	// teste
	@PostMapping("inserir")
	public ResponseEntity<Void> inserirRecebedorTemp(@RequestHeader("idConta") Long idConta,
			@Valid @RequestBody RecebedorTempDTO recebedorTempoDTO) {

		recebedorTempoDTO.setIdConta(idConta);
		service.inserirRecebedorTemp(recebedorTempoDTO);
		return ResponseEntity.ok().build();
	}
}
