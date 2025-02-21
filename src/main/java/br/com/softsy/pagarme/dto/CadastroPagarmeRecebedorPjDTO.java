package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroPagarmeRecebedorPjDTO {

	@NotNull(message = "idRecebedorTemp não pode ser nulo")
	private Long idRecebedorTemp;

	/////// Dados da TBL_PAGARME_RECEBEDOR_PJ ////////

	private Long idPagarmeRecebedorPj;

	private Long idConta;

	private Long idPagarmeRecebedor;

	private String cnpj;

	@NotNull(message = "O nome fantasia é obrigatório")
	private String nomeFantasia;

	@NotNull(message = "A razão social é obrigatória")
	private String razaoSocial;

	private String site;

	@NotNull(message = "O tipo de empresa é obrigatório")
	private Long idTipoEmpresa;

	@NotNull(message = "A data de fundação é obrigatória")
	private LocalDate dataFundacao;

	@NotNull(message = "A receita anual é obrigatória")
	private BigDecimal receitaAnual;

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
	@Pattern(regexp = "\\d{8}", message = "O CEP deve conter 8 dígitos numéricos")
	private String cep;

	@Size(max = 255, message = "O ponto de referência deve ter no máximo 255 caracteres")
	private String pontoReferencia;

	/////// Dados da TBL_PAGARME_RECEBEDOR ////////

	@Size(min = 12, max = 12, message = "O telefone do Recebedor deve ter exatamente 12 caracteres")
	private String telefone;

	@Size(min = 12, max = 12, message = "O celular deve ter exatamente 12 caracteres")
	private String celular;

	/////// Dados da TBL_PAGARME_RECEBEDOR_PJ_RESP_LEGAL ////////

	@NotNull
	@Size(max = 255)
	private String nomeRespLegal;

	@NotNull
	@Email
	@Size(max = 255)
	private String emailRespLegal;

	@NotNull
	@Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
	private String cpfRespLegal;

	@NotNull
	private LocalDate dataNascimentoRespLegal;

	@NotNull
	@Size(max = 255)
	private String nomeMaeRespLegal;

	@NotNull
	private Long idOcupacao;

	@NotNull
	@DecimalMin(value = "0.01", message = "Renda mensal deve ser um valor positivo")
	private BigDecimal rendaMensal;

	@NotNull
	@Size(max = 255)
	private String enderecoRespLegal;

	@NotNull
	@Size(max = 10)
	private String numeroRespLegal;

	@Size(max = 255)
	private String complementoRespLegal;

	@NotNull
	@Size(max = 255)
	private String bairroRespLegal;

	@NotNull
	@Size(max = 255)
	private String cidadeRespLegal;

	@NotNull
	@Size(min = 2, max = 2, message = "Estado deve conter exatamente 2 caracteres")
	private String estadoRespLegal;

	@NotNull
	@Pattern(regexp = "\\d{8}", message = "CEP deve conter exatamente 8 dígitos numéricos")
	private String cepRespLegal;

	@Size(max = 255)
	private String pontoReferenciaRespLegal;

	@Size(min = 12, max = 12, message = "Telefone deve conter exatamente 12 caracteres")
	private String telefoneRespLegal;

	@Size(min = 12, max = 12, message = "Celular deve conter exatamente 12 caracteres")
	private String celularRespLegal;

	public CadastroPagarmeRecebedorPjDTO(PagarmeRecebedorPj pagarmeRecebedorPj) {
		this.idPagarmeRecebedorPj = pagarmeRecebedorPj.getIdPagarmeRecebedorPj();
		this.idConta = pagarmeRecebedorPj.getConta() != null ? pagarmeRecebedorPj.getConta().getIdConta() : null;
		this.idPagarmeRecebedor = pagarmeRecebedorPj.getPagarmeRecebedor() != null
				? pagarmeRecebedorPj.getPagarmeRecebedor().getIdPagarmeRecebedor()
				: null;
		this.cnpj = pagarmeRecebedorPj.getCnpj();
		this.nomeFantasia = pagarmeRecebedorPj.getNomeFantasia();
		this.razaoSocial = pagarmeRecebedorPj.getRazaoSocial();
		this.site = pagarmeRecebedorPj.getSite();
		this.idTipoEmpresa = pagarmeRecebedorPj.getTipoEmpresa() != null
				? pagarmeRecebedorPj.getTipoEmpresa().getIdTipoEmpresa()
				: null;
		this.dataFundacao = pagarmeRecebedorPj.getDataFundacao();
		this.receitaAnual = pagarmeRecebedorPj.getReceitaAnual();
		this.idBanco = pagarmeRecebedorPj.getBanco() != null ? pagarmeRecebedorPj.getBanco().getIdBanco() : null;
		this.agencia = pagarmeRecebedorPj.getAgencia();
		this.dvAgencia = pagarmeRecebedorPj.getDvAgencia();
		this.contaBancaria = pagarmeRecebedorPj.getContaBancaria();
		this.dvConta = pagarmeRecebedorPj.getDvConta();
		this.endereco = pagarmeRecebedorPj.getEndereco();
		this.numero = pagarmeRecebedorPj.getNumero();
		this.complemento = pagarmeRecebedorPj.getComplemento();
		this.bairro = pagarmeRecebedorPj.getBairro();
		this.cidade = pagarmeRecebedorPj.getCidade();
		this.estado = pagarmeRecebedorPj.getEstado();
		this.cep = pagarmeRecebedorPj.getCep();
		this.pontoReferencia = pagarmeRecebedorPj.getPontoReferencia();

		PagarmeRecebedor recebedor = pagarmeRecebedorPj.getPagarmeRecebedor();
		if (recebedor != null) {
			this.telefone = recebedor.getTelefone();
			this.celular = recebedor.getCelular();

			if (recebedor.getPagarmeRecebedorPjRespLegal() != null) {
				PagarmeRecebedorPjRespLegal respLegal = recebedor.getPagarmeRecebedorPjRespLegal();

				this.nomeRespLegal = respLegal.getNomeRespLegal();
				this.emailRespLegal = respLegal.getEmailRespLegal();
				this.cpfRespLegal = respLegal.getCpfRespLegal();
				this.dataNascimentoRespLegal = respLegal.getDtNascRespLegal();
				this.nomeMaeRespLegal = respLegal.getNomeMaeRespLegal();
				this.idOcupacao = respLegal.getOcupacao() != null ? respLegal.getOcupacao().getIdOcupacao() : null;
				this.rendaMensal = respLegal.getRendaMensal();
				this.enderecoRespLegal = respLegal.getEnderecoRespLegal();
				this.numeroRespLegal = respLegal.getNumeroRespLegal();
				this.complementoRespLegal = respLegal.getComplementoRespLegal();
				this.bairroRespLegal = respLegal.getBairroRespLegal();
				this.cidadeRespLegal = respLegal.getCidadeRespLegal();
				this.estadoRespLegal = respLegal.getEstadoRespLegal();
				this.cepRespLegal = respLegal.getCepRespLegal();
				this.pontoReferenciaRespLegal = respLegal.getPontoReferenciaRespLegal();
				this.telefoneRespLegal = respLegal.getTelefoneRespLegal();
				this.celularRespLegal = respLegal.getCelularRespLegal();
			}
		}

	}

}