package br.com.softsy.pagarme.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softsy.pagarme.dto.RecebedorTempDTO;
import br.com.softsy.pagarme.model.RecebedorTemp;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecebedorTempDTO {

	private Long idRecebedorTemp;

	private Long idConta;

	private LocalDateTime dataCadastro;

	@NotNull(message = "O campo 'idUsuario' é obrigatório.")
	private Long idUsuario;

	@NotBlank(message = "O campo 'tipoPessoa' é obrigatório e não pode estar vazio.")
	@Pattern(regexp = "^(PF|PJ)$", message = "O campo 'tipoPessoa' deve ser 'PF' para Pessoa Física ou 'PJ' para Pessoa Jurídica.")
	private String tipoPessoa;

	@NotBlank(message = "O campo 'nome' é obrigatório e não pode estar vazio.")
	private String nome;

	@NotBlank(message = "O campo 'documento' é obrigatório e não pode estar vazio.")
	@Size(max = 14, message = "O campo 'documento' deve ter no máximo 14 caracteres.")
	private String documento;


	@NotBlank(message = "O campo 'email' é obrigatório e não pode estar vazio.")
	private String email;

	private String senha;

	private Character transfAutomatica;

	@NotNull(message = "O campo 'transfIntervalo' é obrigatório.")
	private Character transfIntervalo;


	private Integer transfDia;

	@NotNull(message = "O campo 'antecipAut' é obrigatório.")
	private Character antecipAut;

	private Character antecipTp;

	private Integer antecipVolume;

	private Integer antecipDias;

	private String antecipDelay;

}
