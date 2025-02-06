package br.com.softsy.pagarme.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDTO {

	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
}
