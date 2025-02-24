package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.softsy.pagarme.model.Ocupacao;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroPagarmeRecebedorPjRespLegalDTO {

	private Long idPagarmeRecebedorPjRespLegal;

	@OneToOne
	@JoinColumn(name = "ID_PAGARME_RECEBEDOR", referencedColumnName = "ID_PAGARME_RECEBEDOR")
	private PagarmeRecebedor pagarmeRecebedor;
	
	private Long idPagarmeRecebedor;

	@NotNull(message = "O nome do responsável legal é obrigatório")
	private String nomeRespLegal;

	@NotNull(message = "O email do responsável legal é obrigatório")
	private String emailRespLegal;
	
	@NotNull(message = "O CPF do responsável legal é obrigatório")
	private String cpfRespLegal;

	@NotNull(message = "A data de nascimento do responsável legal é obrigatória")
	private LocalDate dataNascimentoRespLegal;

	@NotNull(message = "O nome da mãe do responsável legal é obrigatório")
	private String nomeMaeRespLegal;

	@OneToOne
	@JoinColumn(name = "ID_OCUPACAO", referencedColumnName = "ID_OCUPACAO")
	private Ocupacao ocupacao;
	
	private Long idOcupacao;

	@NotNull(message = "A renda mensal do responsável legal é obrigatória")
	private BigDecimal rendaMensal;

	@NotNull(message = "O endereço do responsável legal é obrigatório")
	private String enderecoRespLegal;

	@NotNull(message = "O número do endereço do responsável legal é obrigatório")
	private String numeroRespLegal;

	private String complementoRespLegal;

	@NotNull(message = "O bairro do responsável legal é obrigatório")
	private String bairroRespLegal;

	@NotNull(message = "A cidade do responsável legal é obrigatória")
	private String cidadeRespLegal;

	@NotNull(message = "O estado do responsável legal é obrigatório")
	private String estadoRespLegal;

	@NotNull(message = "O CEP do responsável legal é obrigatório")
	private String cepRespLegal;

	private String pontoReferenciaRespLegal;

	private String telefoneRespLegal;

	private String celularRespLegal;

	public CadastroPagarmeRecebedorPjRespLegalDTO(PagarmeRecebedorPjRespLegal pagarmeRecebedorPjRespLegal) {
		this.idPagarmeRecebedorPjRespLegal = pagarmeRecebedorPjRespLegal.getIdPagarmeRecebedorPjRespLegal();
		this.idPagarmeRecebedor = pagarmeRecebedorPjRespLegal.getPagarmeRecebedor().getIdPagarmeRecebedor();
		this.nomeRespLegal = pagarmeRecebedorPjRespLegal.getNomeRespLegal();
		this.emailRespLegal = pagarmeRecebedorPjRespLegal.getEmailRespLegal();
		this.cpfRespLegal = pagarmeRecebedorPjRespLegal.getCpfRespLegal();
		this.dataNascimentoRespLegal = pagarmeRecebedorPjRespLegal.getDtNascRespLegal();
		this.nomeMaeRespLegal = pagarmeRecebedorPjRespLegal.getNomeMaeRespLegal();
		this.idOcupacao = pagarmeRecebedorPjRespLegal.getOcupacao() != null
				? pagarmeRecebedorPjRespLegal.getOcupacao().getIdOcupacao()
				: null;
		this.rendaMensal = pagarmeRecebedorPjRespLegal.getRendaMensal();
		this.enderecoRespLegal = pagarmeRecebedorPjRespLegal.getEnderecoRespLegal();
		this.numeroRespLegal = pagarmeRecebedorPjRespLegal.getNumeroRespLegal();
		this.complementoRespLegal = pagarmeRecebedorPjRespLegal.getComplementoRespLegal();
		this.bairroRespLegal = pagarmeRecebedorPjRespLegal.getBairroRespLegal();
		this.cidadeRespLegal = pagarmeRecebedorPjRespLegal.getCidadeRespLegal();
		this.estadoRespLegal = pagarmeRecebedorPjRespLegal.getEstadoRespLegal();
		this.cepRespLegal = pagarmeRecebedorPjRespLegal.getCepRespLegal();
		this.pontoReferenciaRespLegal = pagarmeRecebedorPjRespLegal.getPontoReferenciaRespLegal();
		this.telefoneRespLegal = pagarmeRecebedorPjRespLegal.getTelefoneRespLegal();
		this.celularRespLegal = pagarmeRecebedorPjRespLegal.getCelularRespLegal();
	}
}
