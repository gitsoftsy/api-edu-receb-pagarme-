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
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import br.com.softsy.pagarme.response.CpfResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorPfService;

@RestController
@RequestMapping("/recebedores")

public class PagarmeRecebedorPfController {

	@Autowired
	private PagarmeRecebedorPfService service;

	@GetMapping
	public ResponseEntity<List<PagarmeRecebedorPF>> listar() {
		return ResponseEntity.ok(service.listarTodos());
	}

	@GetMapping("/existByCpf")
	public ResponseEntity<CpfResponse> verificarCpf(@RequestHeader(value = "idConta", required = false) Long idConta,
			@RequestParam(value = "cpf", required = false) String cpf) {

		if (idConta == null) {
			return ResponseEntity.badRequest()
					.body(new CpfResponse(false, null, null, "O header 'idConta' é obrigatório."));
		}

		if (cpf == null || cpf.trim().isEmpty()) {
			return ResponseEntity.badRequest()
					.body(new CpfResponse(false, null, null, "O parâmetro 'cpf' é obrigatório."));
		}

		CpfResponse response = service.verificarCpf(cpf, idConta);
		return ResponseEntity.ok(response);
	}
}
