package br.com.softsy.pagarme.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.dto.RecebedorTempDTO;
import br.com.softsy.pagarme.infra.config.PasswordEncrypt;
import br.com.softsy.pagarme.dto.CadastroRecebedorTempDTO;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.service.RecebedorTempService;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;

@RestController
@RequestMapping("/recebedorTemp")
public class RecebedorTempController {

	@Autowired
	private RecebedorTempService service;

	@GetMapping
	public ResponseEntity<Map<String, Object>> listar() {

		List<RecebedorTemp> lista = service.listarTudo();

		List<Map<String, Object>> respostaFormatada = lista.stream().map(service::formatarRecebedorTemp)
				.collect(Collectors.toList());

		Map<String, Object> respostaFinal = new LinkedHashMap<>();
		respostaFinal.put("mensagem", "Dados encontrados:");
		respostaFinal.put("quantidade", respostaFormatada.size());
		respostaFinal.put("dados", respostaFormatada);

		return ResponseEntity.ok(respostaFinal);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> inserirRecebedorTemp(
			@RequestHeader(value = "idConta", required = false) Long idConta,
			@Valid @RequestBody CadastroRecebedorTempDTO recebedorTempoDTO) {

		try {
			if (idConta == null) {
				throw new IllegalArgumentException("O campo idConta é obrigatório.");
			}

			recebedorTempoDTO.setIdConta(idConta);
			RecebedorTemp recebedorCriado = service.inserirRecebedorTemp(recebedorTempoDTO);

			Map<String, Object> respostaFinal = new LinkedHashMap<>();
			respostaFinal.put("mensagem", "Recebedor inserido com sucesso!");
			respostaFinal.put("quantidade", 1);
			respostaFinal.put("dados", service.formatarRecebedorTemp(recebedorCriado));

			return ResponseEntity.ok(respostaFinal);

		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		}
	}

	@GetMapping("/{idRecebedorTemp}")
	public ResponseEntity<Map<String, Object>> obterRecebedorTemp(@RequestHeader("idConta") Long idConta,
			@PathVariable Long idRecebedorTemp) {
		try {
			Map<String, Object> resposta = service.obterRecebedorTemp(idConta, idRecebedorTemp);
			return ResponseEntity.ok(resposta);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		}
	}

}
