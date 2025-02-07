package br.com.softsy.pagarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.response.CnpjResponse;
import br.com.softsy.pagarme.service.PagarmeRecebedorPjService;

@RestController
@RequestMapping("/recebedorPj")
public class PagarmeRecebedorPjController {

	@Autowired
	private PagarmeRecebedorPjService recebedorPjService;

	 @GetMapping("recebedores/existByCnpj")
	    public ResponseEntity<CnpjResponse> verificarCnpj(
	            @RequestHeader(value = "idConta", required = false) Long idConta,
	            @RequestParam(value = "cnpj", required = false) String cnpj) {

	        if (idConta == null) {
	            return ResponseEntity.badRequest()
	                    .body(new CnpjResponse(false, null, null, "O header 'idConta' é obrigatório."));
	        }

	        if (cnpj == null || cnpj.trim().isEmpty()) {
	            return ResponseEntity.badRequest()
	                    .body(new CnpjResponse(false, null, null, "O parâmetro 'cnpj' é obrigatório."));
	        }

	        CnpjResponse response = recebedorPjService.verificarCnpj(cnpj, idConta);
	        return ResponseEntity.ok(response);
	    }

}
