package br.com.softsy.pagarme.security;

import br.com.softsy.pagarme.dto.LoginDTO;
import br.com.softsy.pagarme.infra.exception.RecebedorDesativadoException;
import br.com.softsy.pagarme.infra.exception.SenhaInvalidaException;

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

	public RecebedorAutenticador(PagarmeRecebedorRepository repository, PasswordEncrypt encrypt,
			RecebedorTempRepository tempRepository) {
		this.repository = repository;
		this.encrypt = encrypt;
		this.tempRepository = tempRepository;
	}

	@Override
	public RecebedorLoginDTO autenticar(LoginDTO login) {

		RecebedorTemp recebedorTemp = tempRepository.findRecebedorTempByEmail(login.getEmail());

		if (recebedorTemp != null) {

			if (!encrypt.checkPass(login.getSenha(), recebedorTemp.getSenha())) {
				throw new SenhaInvalidaException("Senha incorreta.");
			}

			Long idConta = recebedorTemp.getConta() != null ? recebedorTemp.getConta().getIdConta() : null;

			return new RecebedorLoginDTO(recebedorTemp.getIdRecebedorTemp(), recebedorTemp.getNome(),
					recebedorTemp.getDocumento(), "Temporária", idConta, "Temporário"

			);
		}

		PagarmeRecebedor recebedor = repository.findPagarmeRecebedorByEmailAndAtivo(login.getEmail(), 'S');

		if (recebedor != null) {

			if (!encrypt.checkPass(login.getSenha(), recebedor.getSenha())) {
				throw new SenhaInvalidaException("Senha incorreta.");
			}

			String nome = null;
			String documento = null;
			String nomeFantasia = null;

			if (recebedor.getPagarmeRecebedorPjRespLegal() != null) {
				nome = recebedor.getPagarmeRecebedorPjRespLegal().getNomeRespLegal();
			}

			if (recebedor.getPagarmeRecebedorPj() != null) {
				documento = recebedor.getPagarmeRecebedorPj().getCnpj();
			}

			if (recebedor.getPagarmeRecebedorPj() != null) {
				nomeFantasia = recebedor.getPagarmeRecebedorPj().getNomeFantasia();
			}

			Long idConta = recebedor.getConta() != null ? recebedor.getConta().getIdConta() : null;

			return new RecebedorLoginDTO(recebedor.getIdPagarmeRecebedor(), nome, documento, "Principal", idConta,
					nomeFantasia

			);
		}

		throw new RecebedorDesativadoException("Usuário não encontrado.");
	}

}
