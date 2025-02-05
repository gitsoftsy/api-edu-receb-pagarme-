package br.com.softsy.pagarme.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.response.EmailResponse;

@Service
public class PagarmeRecebedorService {

	@Autowired
	private PagarmeRecebedorRepository repository;
	
	@Autowired
    private EntityManager entityManager;

	@Autowired
	private RecebedorTempRepository recebedorTempRepository;

	public List<PagarmeRecebedor> listarTudo() {
		return repository.findAll();
	}

	public EmailResponse verificarEmail(String email, Long idConta) {

		boolean contaExiste = repository.existsById(idConta) || recebedorTempRepository.existsById(idConta);

		if (!contaExiste) {
			return new EmailResponse(false, null, null, "Conta inválida ou inexistente.");
		}

		Optional<RecebedorTemp> recebedorTemp = recebedorTempRepository.findByEmail(email);
		if (recebedorTemp.isPresent()) {
			return new EmailResponse(true, recebedorTemp.get().getIdRecebedorTemp(), "TBL_RECEBEDOR_TEMP",
					"Dados encontrados");
		}

		Optional<PagarmeRecebedor> pagarmeRecebedor = repository.findByEmail(email);
		if (pagarmeRecebedor.isPresent()) {
			return new EmailResponse(true, pagarmeRecebedor.get().getIdPagarmeRecebedor(), "TBL_PAGARME_RECEBEDOR",
					"Dados encontrados");
		}

		return new EmailResponse(false, null, null, null);
	}
	
	
	public List<Map<String, Object>> filtrarRecebedores (Long idConta, String documento,String nome) {
        StringBuilder sql = new StringBuilder();
        sql.append("CALL PROC_FILTRAR_RECEBEDORES(:pIdConta, :pDocumento, :pNome)");
 
        Query query = entityManager.createNativeQuery(sql.toString());
 
      
        query.setParameter("pIdConta", idConta);
        query.setParameter("pDocumento", documento);
        query.setParameter("pNome",nome);
        
 
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

}
