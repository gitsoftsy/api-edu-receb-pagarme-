package br.com.softsy.pagarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softsy.pagarme.dto.LoginDTO;
import br.com.softsy.pagarme.dto.RecebedorLoginDTO;
import br.com.softsy.pagarme.security.RecebedorAutenticador.RecebedorDesativadoException;
import br.com.softsy.pagarme.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping
    public ResponseEntity<Object> autenticar(@RequestBody LoginDTO login) {
        try {
        	RecebedorLoginDTO loginRecebedor = loginService.autenticar(login);
            return ResponseEntity.ok(loginRecebedor);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("O email ou a senha são inválidos");
        } catch (RecebedorDesativadoException e) {
        	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
        }
    }
	
	public class ErrorResponse {
		private String message;

		public ErrorResponse(String message) {
			this.message = message;
		}

		// Getter e Setter
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
}
