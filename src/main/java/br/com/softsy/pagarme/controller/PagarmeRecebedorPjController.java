package br.com.softsy.pagarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.service.PagarmeRecebedorPjService;

@RestController
@RequestMapping("/recebedorPj")
public class PagarmeRecebedorPjController {

	@Autowired
	private PagarmeRecebedorPjService recebedorPjService;


}
