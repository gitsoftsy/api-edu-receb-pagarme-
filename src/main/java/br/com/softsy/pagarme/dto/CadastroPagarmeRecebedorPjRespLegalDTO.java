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

	@NotNull
	private String nomeRespLegal;

	@NotNull
	private String emailRespLegal;

	@NotNull
	private String cpfRespLegal;

	@NotNull
	private LocalDate dataNascimentoRespLegal;

	@NotNull
	private String nomeMaeRespLegal;

	@OneToOne
	@JoinColumn(name = "ID_OCUPACAO", referencedColumnName = "ID_OCUPACAO")
	private Ocupacao ocupacao;
	
	private Long idOcupacao;

	@NotNull
	private BigDecimal rendaMensal;

	@NotNull
	private String enderecoRespLegal;

	@NotNull
	private String numeroRespLegal;

	private String complementoRespLegal;

	@NotNull
	private String bairroRespLegal;

	@NotNull
	private String cidadeRespLegal;

	@NotNull
	private String estadoRespLegal;

	@NotNull
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
