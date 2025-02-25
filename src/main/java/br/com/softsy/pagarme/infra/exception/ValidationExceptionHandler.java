package br.com.softsy.pagarme.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UniqueException.class)
	public ResponseEntity<Map<String, Object>> handleUniqueException(UniqueException e) {
		Map<String, Object> resposta = new HashMap<>();
		resposta.put("mensagem", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
	    Map<String, Object> resposta = new HashMap<>();
	    resposta.put("mensagem", e.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}

}