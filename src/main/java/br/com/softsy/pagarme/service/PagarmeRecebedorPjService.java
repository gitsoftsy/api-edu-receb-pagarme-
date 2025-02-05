package br.com.softsy.pagarme.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.repository.BancoRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.repository.OcupacaoRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorPjRespLegalRepository;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;

@Service
public class PagarmeRecebedorPjService {

	@Autowired
	private PagarmeRecebedorRepository recebedorRepository;

	@Autowired
	private PagarmeRecebedorPjRepository recebedorPjRepository;

	@Autowired
	private PagarmeRecebedorPjRespLegalRepository recebedorRespLegalRepository;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;


}
