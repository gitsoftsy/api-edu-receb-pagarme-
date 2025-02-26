package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroPagarmeRecebedorPfDTO {

	private Long idRecebedorTemp;

	/////// Dados da TBL_PAGARME_RECEBEDOR_PF ////////

	private Long idPagarmeRecebedorPf;

	private Long idConta;

	private Long idPagarmeRecebedor;

	private String site;

	@NotNull(message = "O nome da mãe é obrigatório")
	private String nomeDaMae;

	@NotNull(message = "A data de nascimento é obrigatória")
	private LocalDate dataNascimento;

	@NotNull(message = "A ocupação é obrigatória")
	private Long idOcupacao;

	@NotNull(message = "A renda mensal é obrigatória")
	@DecimalMin(value = "0.01", message = "A renda mensal deve ser um valor positivo")
	private BigDecimal rendaMensal;

	@NotNull(message = "O banco é obrigatório")
	private Long idBanco;

	@NotNull(message = "A agência bancária é obrigatória")
	private String agencia;

	@NotNull(message = "O dígito da agência é obrigatório")
	private String dvAgencia;

	@NotNull(message = "A conta bancária é obrigatória")
	private String contaBancaria;

	@NotNull(message = "O dígito da conta bancária é obrigatório")
	private String dvConta;

	@NotNull(message = "O endereço é obrigatório")
	private String endereco;

	@NotNull(message = "O número do endereço é obrigatório")
	private String numero;

	private String complemento;

	@NotNull(message = "O bairro é obrigatório")
	private String bairro;

	@NotNull(message = "A cidade é obrigatória")
	private String cidade;

	@NotNull(message = "O estado é obrigatório")
	@Size(min = 2, max = 2, message = "O estado deve ter exatamente 2 caracteres")
	private String estado;

	@NotNull(message = "O CEP é obrigatório")
	@Pattern(regexp = "\\d{8}", message = "O CEP deve conter exatamente 8 dígitos numéricos")
	private String cep;

	@Size(max = 255, message = "O ponto de referência deve ter no máximo 255 caracteres")
	private String pontoReferencia;

	/////// Dados da TBL_PAGARME_RECEBEDOR ////////

	@Size(min = 12, max = 12, message = "O telefone do Recebedor deve ter exatamente 12 caracteres")
	private String telefone;

	@Size(min = 12, max = 12, message = "O celular deve ter exatamente 12 caracteres")
	private String celular;

	public CadastroPagarmeRecebedorPfDTO(PagarmeRecebedorPF pagarmeRecebedorPF) {
		this.idPagarmeRecebedorPf = pagarmeRecebedorPF.getIdPagarmeRecebedorPF();
		this.idConta = pagarmeRecebedorPF.getEntidadeConta() != null
				? pagarmeRecebedorPF.getEntidadeConta().getIdConta()
				: null;
		this.idPagarmeRecebedor = pagarmeRecebedorPF.getPagarmeRecebedor() != null
				? pagarmeRecebedorPF.getPagarmeRecebedor().getIdPagarmeRecebedor()
				: null;
		this.site = pagarmeRecebedorPF.getSite();
		this.nomeDaMae = pagarmeRecebedorPF.getNomeDaMae();
		this.dataNascimento = pagarmeRecebedorPF.getDataNascimento();
		this.idOcupacao = pagarmeRecebedorPF.getOcupacao() != null ? pagarmeRecebedorPF.getOcupacao().getIdOcupacao()
				: null;
		this.rendaMensal = pagarmeRecebedorPF.getRendaMensal();
		this.idBanco = pagarmeRecebedorPF.getBanco() != null ? pagarmeRecebedorPF.getBanco().getIdBanco() : null;
		this.agencia = pagarmeRecebedorPF.getAgencia();
		this.dvAgencia = pagarmeRecebedorPF.getDvAgencia();		
		this.contaBancaria = pagarmeRecebedorPF.getNumeroConta(); // é o campo "Conta" da TBL_PAGARME_RECEBEDOR_PF (deixei assim pq estava dando conflito)
		this.dvConta = pagarmeRecebedorPF.getDvConta();
		this.endereco = pagarmeRecebedorPF.getEndereco();
		this.numero = pagarmeRecebedorPF.getNumero();
		this.complemento = pagarmeRecebedorPF.getComplemento();
		this.bairro = pagarmeRecebedorPF.getBairro();
		this.cidade = pagarmeRecebedorPF.getCidade();
		this.estado = pagarmeRecebedorPF.getEstado();
		this.cep = pagarmeRecebedorPF.getCep();
		this.pontoReferencia = pagarmeRecebedorPF.getPontoReferencia();

		if (pagarmeRecebedorPF.getPagarmeRecebedor() != null) {
			this.telefone = pagarmeRecebedorPF.getPagarmeRecebedor().getTelefone();
			this.celular = pagarmeRecebedorPF.getPagarmeRecebedor().getCelular();
		}
	}

}