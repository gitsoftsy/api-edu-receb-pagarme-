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

import br.com.softsy.pagarme.dto.TipoEmpresaDTO;
import br.com.softsy.pagarme.service.TipoEmpresaService;

@RestController
@RequestMapping("/tipoEmpresa")
public class TipoEmpresaController {

	@Autowired
	private TipoEmpresaService tipoEmpresaService;

	@GetMapping
	public ResponseEntity<Map<String, List<TipoEmpresaDTO>>> listar() {
		List<TipoEmpresaDTO> tiposEmpresas = tipoEmpresaService.listarTudo();

		Map<String, List<TipoEmpresaDTO>> response = new HashMap<>();
		response.put("tiposEmpresa", tiposEmpresas);

		return ResponseEntity.ok(response);
	}
}