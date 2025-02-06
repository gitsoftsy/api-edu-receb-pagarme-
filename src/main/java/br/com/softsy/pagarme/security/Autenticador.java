package br.com.softsy.pagarme.security;

import br.com.softsy.pagarme.dto.LoginDTO;
import br.com.softsy.pagarme.dto.RecebedorLoginDTO;

public interface Autenticador {
	RecebedorLoginDTO autenticar(LoginDTO login);
}
