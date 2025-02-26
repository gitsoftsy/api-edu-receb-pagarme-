package br.com.softsy.pagarme.controller;

import java.net.URI;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softsy.pagarme.dto.CadastroPagarmeRecebedorPfDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.response.CpfResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorPfService;

@RestController
@RequestMapping("/recebedoresPf")

public class PagarmeRecebedorPfController {

	@Autowired
	private PagarmeRecebedorPfService service;

	@GetMapping
	public ResponseEntity<List<PagarmeRecebedorPF>> listar() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/{idRecebedorPf}")
	public ResponseEntity<Map<String, Object>> buscarRecebedorPf(@PathVariable Long idRecebedorPf) {
		try {
			Map<String, Object> resposta = service.buscarRecebedorPfPorId(idRecebedorPf);
			return ResponseEntity.ok(resposta);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("mensagem", "Recebedor PF não encontrado."));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("mensagem", "Erro ao buscar recebedor PF."));
		}
	}

	@GetMapping("/existByCpf")
	public ResponseEntity<CpfResponse> verificarCpf(@RequestHeader(value = "idConta", required = false) Long idConta,
			@RequestParam(value = "cpf", required = false) String cpf) {

		if (idConta == null) {
			throw new IllegalArgumentException("O headers 'idConta' é obrigatório.");
		}

		if (cpf == null || cpf.trim().isEmpty()) {
			throw new IllegalArgumentException("O parâmetro 'cpf' é obrigatório.");
		}

		CpfResponse response = service.verificarCpf(cpf, idConta);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> inserirRecebedorPF(
			@RequestHeader(value = "idConta", required = false) Long headerIdConta,
			@Valid @RequestBody CadastroPagarmeRecebedorPfDTO cadastroRecebedorPfDTO) {

		try {
			if (headerIdConta == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Collections.singletonMap("mensagem", "O idConta é obrigatório."));
			}

			if (cadastroRecebedorPfDTO.getIdRecebedorTemp() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Collections.singletonMap("mensagem", "O ID do Recebedor Temporário não pode ser nulo."));
			}

			PagarmeRecebedorPF recebedorCriado = service.inserirRecebedorPF(cadastroRecebedorPfDTO.getIdRecebedorTemp(),
					headerIdConta, cadastroRecebedorPfDTO);

			Map<String, Object> respostaFinal = new LinkedHashMap<>();
			respostaFinal.put("mensagem", "Recebedor PF inserido com sucesso!");
			respostaFinal.put("quantidade", 1);
			respostaFinal.put("dados", service.formatarRetorno(recebedorCriado));

			return ResponseEntity.ok(respostaFinal);

		} catch (UniqueException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("mensagem", "Erro interno ao inserir o Recebedor PF."));
		}
	}

}
