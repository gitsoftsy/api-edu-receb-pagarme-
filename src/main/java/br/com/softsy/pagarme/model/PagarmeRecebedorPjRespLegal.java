package br.com.softsy.pagarme.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_PAGARME_RECEBEDOR_PJ_RESP_LEGAL")
@Data
public class PagarmeRecebedorPjRespLegal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGARME_RECEBEDOR_PJ_RESP_LEGAL")
	private Long idPagarmeRecebedorPjRespLegal;

	@OneToOne
	@JoinColumn(name = "ID_PAGARME_RECEBEDOR", referencedColumnName = "ID_PAGARME_RECEBEDOR")
	private PagarmeRecebedor pagarmeRecebedor;

	@Column(name = "NOME_RESP_LEGAL", length = 255, nullable = false)
	private String nomeRespLegal;

	@Column(name = "EMAIL_RESP_LEGAL", length = 255, nullable = false)
	private String emailRespLegal;

	@Column(name = "CPF_RESP_LEGAL", length = 11, nullable = false)
	private String cpfRespLegal;

	@Column(name = "DT_NASC_RESP_LEGAL")
	private LocalDate dtNascRespLegal;

	@Column(name = "NOME_MAE_RESP_LEGAL", length = 255)
	private String nomeMaeRespLegal;

	@ManyToOne
	@JoinColumn(name = "ID_OCUPACAO")
	private Ocupacao ocupacao;

	@Column(name = "RENDA_MENSAL", precision = 15, scale = 2)
	private BigDecimal rendaMensal;

	@Column(name = "ENDERECO_RESP_LEGAL", length = 255)
	private String enderecoRespLegal;

	@Column(name = "NUMERO_RESP_LEGAL", length = 10)
	private String numeroRespLegal;

	@Column(name = "COMPLEMENTO_RESP_LEGAL", length = 255)
	private String complementoRespLegal;

	@Column(name = "BAIRRO_RESP_LEGAL", length = 255)
	private String bairroRespLegal;

	@Column(name = "CIDADE_RESP_LEGAL", length = 255)
	private String cidadeRespLegal;

	@Column(name = "ESTADO_RESP_LEGAL", length = 2)
	private String estadoRespLegal;

	@Column(name = "CEP_RESP_LEGAL", length = 8)
	private String cepRespLegal;

	@Column(name = "PONTO_REFERENCIA_RESP_LEGAL", length = 255)
	private String pontoReferenciaRespLegal;

	@Column(name = "TELEFONE_RESP_LEGAL", length = 12)
	private String telefoneRespLegal;

	@Column(name = "CELULAR_RESP_LEGAL", length = 12)
	private String celularRespLegal;
}