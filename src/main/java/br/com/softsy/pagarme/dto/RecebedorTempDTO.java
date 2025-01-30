package br.com.softsy.pagarme.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

	@NotNull
	private Long idUsuario;

	@NotBlank(message = "O campo 'tipoPessoa' é obrigatório e não pode estar vazio.")
	private String tipoPessoa;

	@NotNull
	private String nome;

	@NotNull
	@Size(max = 14)
	private String documento;

	@NotNull
	private String email;

	private String senha;

	private Character transfAutomatica;

	private Character transfIntervalo;

	private Integer transfDia;

	@NotNull
	private Character antecipAut;

	private Character antecipTp;

	private Integer antecipVolume;

	private Integer antecipDias;

	private String antecipDelay;

}
