package br.com.softsy.pagarme.controller;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.dto.CadastroPagarmeRecebedorPjDTO;
import br.com.softsy.pagarme.infra.exception.UniqueException;
import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import br.com.softsy.pagarme.response.CnpjResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorPjService;

@RestController
@RequestMapping("/recebedorPj")
@Validated
public class PagarmeRecebedorPjController {

	@Autowired
	private PagarmeRecebedorPjService recebedorPjService;

	@GetMapping("recebedores/existByCnpj")
	public ResponseEntity<CnpjResponse> verificarCnpj(@RequestHeader(value = "idConta", required = false) Long idConta,
			@RequestParam(value = "cnpj", required = false) String cnpj) {

		if (idConta == null) {
			throw new IllegalArgumentException("O header 'idConta' é obrigatório.");
		}

		if (cnpj == null || cnpj.trim().isEmpty()) {
			throw new IllegalArgumentException("O parâmetro 'cnpj' é obrigatório.");
		}

		CnpjResponse response = recebedorPjService.verificarCnpj(cnpj, idConta);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> inserirRecebedorPJ(
			@RequestHeader(value = "idConta", required = false) Long headerIdConta,
			@Valid @RequestBody CadastroPagarmeRecebedorPjDTO cadastroRecebedorPjDTO) {

		try {
			if (headerIdConta == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Collections.singletonMap("mensagem", "O idConta é obrigatório."));
			}

			if (cadastroRecebedorPjDTO.getIdRecebedorTemp() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(Collections.singletonMap("mensagem", "O ID do Recebedor Temporário não pode ser nulo."));
			}

			PagarmeRecebedorPj recebedorCriado = recebedorPjService.inserirRecebedorPJ(
					cadastroRecebedorPjDTO.getIdRecebedorTemp(), headerIdConta, cadastroRecebedorPjDTO);

			Map<String, Object> respostaFinal = new LinkedHashMap<>();
			respostaFinal.put("mensagem", "Recebedor PJ inserido com sucesso!");
			respostaFinal.put("quantidade", 1);
			respostaFinal.put("dados", recebedorPjService.formatarRetorno(recebedorCriado));

			return ResponseEntity.ok(respostaFinal);

		} catch (UniqueException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("mensagem", e.getMessage()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)

					.body(Collections.singletonMap("mensagem", "Erro interno ao inserir o Recebedor PJ."));
		}
	}

}