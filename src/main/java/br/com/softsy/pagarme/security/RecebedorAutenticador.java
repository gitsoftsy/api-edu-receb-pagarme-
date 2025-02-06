package br.com.softsy.pagarme.security;

import br.com.softsy.pagarme.dto.LoginDTO;
import br.com.softsy.pagarme.dto.RecebedorLoginDTO;
import br.com.softsy.pagarme.infra.config.PasswordEncrypt;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.RecebedorTemp;
import br.com.softsy.pagarme.repository.PagarmeRecebedorRepository;
import br.com.softsy.pagarme.repository.RecebedorTempRepository;

public class RecebedorAutenticador implements Autenticador {

	private PagarmeRecebedorRepository repository;
	private RecebedorTempRepository tempRepository;
	private PasswordEncrypt encrypt;
	
	public RecebedorAutenticador(PagarmeRecebedorRepository repository, PasswordEncrypt encrypt, RecebedorTempRepository tempRepository) {
		this.repository = repository;
		this.encrypt = encrypt;
		this.tempRepository = tempRepository;
	}
	
	@Override
	public RecebedorLoginDTO autenticar(LoginDTO login) {
	    PagarmeRecebedor recebedor = repository.findPagarmeRecebedorByEmailAndAtivo(login.getEmail(), 'S');
	    
	    RecebedorTemp recebedorTemp = tempRepository.findRecebedorTempByEmail(login.getEmail());

	    if (recebedor == null || recebedorTemp == null) {
	        throw new RecebedorDesativadoException("Recebedor deve existir tanto na tabela principal quanto na temporária.");
	    }

	    if (!encrypt.checkPass(login.getSenha(), recebedor.getSenha())) {
	        throw new SenhaInvalidaException("Senha incorreta.");
	    }

	    return new RecebedorLoginDTO(
	        recebedor.getIdPagarmeRecebedor(),
	        recebedorTemp.getNome(),
	        recebedorTemp.getDocumento(),
	        "Teste"
	    );
	}

	
	public class RecebedorDesativadoException extends RuntimeException {
		public RecebedorDesativadoException(String message) {
			super(message);
		}
	}
	
	public class SenhaInvalidaException extends RuntimeException {
	    public SenhaInvalidaException(String message) {
	        super(message);
	    }
	}
}
