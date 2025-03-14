package br.com.softsy.pagarme.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.repository.ContaRepository;
import br.com.softsy.pagarme.response.EmailResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagarmeRecebedorService {

	@Autowired
	private PagarmeRecebedorRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	@Autowired
	private ContaRepository contaRepository;

	   public Page<PagarmeRecebedor> listarTudo(Pageable pageable) {
	        return repository.findAll(pageable);
	    }

	public EmailResponse verificarEmail(String email, Long idConta) {

		if (!contaRepository.existsById(idConta)) {
			return new EmailResponse(false, null, null, "Conta inválida ou inexistente.");
		}

		boolean contaExisteEmRecebedorTemp = recebedorTempRepository.existsByConta_IdConta(idConta);
		boolean contaExisteEmPagarmeRecebedor = repository.existsByConta_IdConta(idConta);

		if (!contaExisteEmRecebedorTemp && !contaExisteEmPagarmeRecebedor) {
			return new EmailResponse(false, null, null, "ID da conta não encontrado em nenhuma tabela de recebedores.");
		}

		return recebedorTempRepository.findByEmail(email)
				.map(recebedorTemp -> new EmailResponse(true, recebedorTemp.getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
						"Dados encontrados"))
				.or(() -> repository.findByEmail(email)
						.map(pagarmeRecebedor -> new EmailResponse(true, pagarmeRecebedor.getIdPagarmeRecebedor(),
								"TBL_PAGARME_RECEBEDOR", "Dados encontrados")))
				.orElse(new EmailResponse(false, null, null, "E-mail não encontrado em nenhuma tabela."));
	}

	public List<Map<String, Object>> filtrarRecebedores(Long idConta, String documento, String nome) {
		StringBuilder sql = new StringBuilder();
		sql.append("CALL PROC_FILTRAR_RECEBEDORES(:pIdConta, :pDocumento, :pNome)");

		Query query = entityManager.createNativeQuery(sql.toString());

		query.setParameter("pIdConta", idConta);
		query.setParameter("pDocumento", documento);
		query.setParameter("pNome", nome);

		List<Object[]> resultList = query.getResultList();
		List<Map<String, Object>> mappedResultList = new ArrayList<>();

		for (Object[] result : resultList) {
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("idRecebedor", result[0]);
			resultMap.put("documento", result[1]);
			resultMap.put("nome", result[2]);
			resultMap.put("statusRecebedor", result[3]);
			resultMap.put("tabela", result[4]);
			resultMap.put("tipoPessoa", result[5]);

			mappedResultList.add(resultMap);
		}

		return mappedResultList;
	}

	//ativar desativar recebedor
	@Transactional
	public void atualizarStatusRecebedor(Long idRecebedor, String ativo) {
		if (!ativo.equalsIgnoreCase("S") && !ativo.equalsIgnoreCase("N")) {
			throw new IllegalArgumentException("O status deve ser 'S' (ativo) ou 'N' (inativo).");
		}

		PagarmeRecebedor recebedor = repository.findById(idRecebedor)
				.orElseThrow(() -> new IllegalArgumentException("idRecebedor não encontrado."));

		recebedor.setAtivo(ativo.toUpperCase().charAt(0));
		repository.save(recebedor);
	}

}
