package br.com.softsy.pagarme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.dto.LoginDTO;
import br.com.softsy.pagarme.dto.RecebedorLoginDTO;
import br.com.softsy.pagarme.infra.config.PasswordEncrypt;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;
import br.com.softsy.pagarme.security.Autenticador;
import br.com.softsy.pagarme.security.RecebedorAutenticador;


@Service
public class LoginService {
	
	@Autowired
	private PagarmeRecebedorRepository repository;
	
	@Autowired
	private RecebedorTempRepository tempRepository;

	@Autowired
	private PasswordEncrypt encrypt;
	
	public RecebedorLoginDTO autenticar(LoginDTO login) {
		Autenticador autenticador = null;
		
		autenticador = new RecebedorAutenticador(repository, encrypt, tempRepository);
		
		return autenticador.autenticar(login);
	}

}
