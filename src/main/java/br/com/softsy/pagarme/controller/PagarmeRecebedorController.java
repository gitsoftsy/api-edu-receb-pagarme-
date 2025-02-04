package br.com.softsy.pagarme.controller;

import java.net.URI;
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

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.response.EmailResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorService;

@RestController
@RequestMapping("/pagarmeRecebedor")
public class PagarmeRecebedorController {

	@Autowired
	private PagarmeRecebedorService service;

	@GetMapping
	public ResponseEntity<List<PagarmeRecebedor>> listar() {
		return ResponseEntity.ok(service.listarTudo());
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

}
