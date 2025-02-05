package br.com.softsy.pagarme.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroPagarmeRecebedorDTO {
	private Long idPagarmeRecebedor;

	private Long idConta;

	private LocalDateTime dataCadastro;

	@NotNull(message = "O campo 'idUsuario' é obrigatório.")
	private Long idUsuario;

	@NotBlank()
	@Pattern(regexp = "^(PF|PJ)$", message = "O campo 'tipoPessoa' deve ser 'PF' para Pessoa Física ou 'PJ' para Pessoa Jurídica.")
	private String tipoPessoa;

	private Character ativo;

	@NotBlank(message = "O campo 'email' é obrigatório e não pode estar vazio.")
	private String email;

	private String senha;

	private Character transfAutomatica;

	private Character transfIntervalo;

	private Integer transfDia;

	private Character antecipAut;

	private Character antecipTp;

	private Integer antecipVolume;

	private Integer antecipDias;

	private String antecipDelay;

	private String cdPagarmeRecebedor;

	private String telefone;

	private String celular;

	public CadastroPagarmeRecebedorDTO(PagarmeRecebedor pagarmeRecebedor) {
		this.idPagarmeRecebedor = pagarmeRecebedor.getIdPagarmeRecebedor();
		this.idConta = (pagarmeRecebedor.getConta() != null) ? pagarmeRecebedor.getConta().getIdConta() : null;
		this.dataCadastro = pagarmeRecebedor.getDataCadastro();
		this.idUsuario = (pagarmeRecebedor.getUsuario() != null) ? pagarmeRecebedor.getUsuario().getIdUsuario() : null;
		this.tipoPessoa = pagarmeRecebedor.getTipoPessoa();
		this.ativo = pagarmeRecebedor.getAtivo();
		this.email = pagarmeRecebedor.getEmail();
		this.senha = null;

		this.transfAutomatica = pagarmeRecebedor.getTransfAutomatica();
		this.transfIntervalo = pagarmeRecebedor.getTransfIntervalo();
		this.transfDia = pagarmeRecebedor.getTransfDia();

		this.antecipAut = pagarmeRecebedor.getAntecipAut();
		this.antecipTp = pagarmeRecebedor.getAntecipTp();
		this.antecipVolume = pagarmeRecebedor.getAntecipVolume();
		this.antecipDias = pagarmeRecebedor.getAntecipDias();
		this.antecipDelay = pagarmeRecebedor.getAntecipDelay();
		this.cdPagarmeRecebedor = pagarmeRecebedor.getCdPagarmeRecebedor();
		this.telefone = pagarmeRecebedor.getTelefone();
		this.celular = pagarmeRecebedor.getCelular();

	}

}
