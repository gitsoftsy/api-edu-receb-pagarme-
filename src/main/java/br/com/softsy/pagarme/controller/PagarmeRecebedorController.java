package br.com.softsy.pagarme.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.model.AllResponse;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.response.EmailResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/pagarmeRecebedor")
public class PagarmeRecebedorController {

	@Autowired
	private PagarmeRecebedorService service;

	   @GetMapping
	    public ResponseEntity<Page<PagarmeRecebedor>> listar(Pageable pageable) {
	        Page<PagarmeRecebedor> recebedores = service.listarTudo(pageable);
	        return ResponseEntity.ok(recebedores);
	    }

	@GetMapping("/recebedores/existByEmail")
	public ResponseEntity<EmailResponse> verificarEmail(
			@RequestHeader(value = "idConta", required = false) Long idConta,
			@RequestParam(value = "email", required = false) String email) {

		if (idConta == null) {
			return ResponseEntity.badRequest()
					.body(new EmailResponse(false, null, null, "O header 'idConta' é obrigatório."));
		}

		if (email == null || email.trim().isEmpty()) {
			return ResponseEntity.badRequest()
					.body(new EmailResponse(false, null, null, "O parâmetro 'email' é obrigatório."));
		}

		EmailResponse response = service.verificarEmail(email, idConta);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/recebedores/filtrar")
	public Object filtrarPainelDeVagas(@RequestHeader(value = "idConta", required = false) Long idConta,
			@RequestParam(value = "documento", required = false) String documento,
			@RequestParam(value = "nome", required = false) String nome) {

		if (idConta == null) {

			return ResponseEntity.badRequest().body(
					new AllResponse("Por favor, informe ao menos um parâmetro na requisição.", new ArrayList<>()));
		}

		List<Map<String, Object>> result = service.filtrarRecebedores(idConta, documento, nome);

		if (result.isEmpty()) {
			return ResponseEntity.ok(
					new AllResponse("Nenhum resultado encontrado para os parâmetros informados.", new ArrayList<>()));
		}

		return ResponseEntity.ok(new AllResponse("Encontrado!", new ArrayList<>(result)));
	}

	@PutMapping("/{idRecebedor}/status")
	public ResponseEntity<Map<String, Object>> atualizarStatusRecebedor(@PathVariable Long idRecebedor,
			@RequestParam String ativo) {

		try {
			service.atualizarStatusRecebedor(idRecebedor, ativo);

			Map<String, Object> resposta = new LinkedHashMap<>();
			resposta.put("mensagem", "Campo ativo do recebedor atualizado com sucesso.");
			resposta.put("idRecebedor", idRecebedor);
			resposta.put("novoStatus", ativo.toUpperCase());

			return ResponseEntity.ok(resposta);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(Collections.singletonMap("mensagem", "Erro interno ao atualizar status do recebedor."));
		}
	}

}
