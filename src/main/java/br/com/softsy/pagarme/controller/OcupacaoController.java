package br.com.softsy.pagarme.controller;

import java.net.URI;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softsy.pagarme.dto.OcupacaoDTO;
import br.com.softsy.pagarme.service.OcupacaoService;

@RestController
@RequestMapping("/ocupacoes")
public class OcupacaoController {

	@Autowired
	private OcupacaoService ocupacaoService;

	@GetMapping
	public ResponseEntity<Map<String, List<OcupacaoDTO>>> listar() {
		List<OcupacaoDTO> ocupacoes = ocupacaoService.listarTudo();

		Map<String, List<OcupacaoDTO>> response = new HashMap<>();
		response.put("ocupacoes", ocupacoes);

		return ResponseEntity.ok(response);
	}
}