package br.com.softsy.pagarme.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.math.BigDecimal;

import br.com.softsy.pagarme.model.Conta;
import br.com.softsy.pagarme.model.PagarmeRecebedor;
import br.com.softsy.pagarme.model.Ocupacao;
import br.com.softsy.pagarme.model.Banco;

import lombok.Data;

@Entity
@Table(name = "TBL_PAGARME_RECEBEDOR_PF")
@Data
public class PagarmeRecebedorPF {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGARME_RECEBEDOR_PF")
	private Long idPagarmeRecebedorPF;

	@ManyToOne
	@JoinColumn(name = "ID_CONTA")
	private Conta entidadeConta;

	@ManyToOne
	@JoinColumn(name = "ID_PAGARME_RECEBEDOR")
	private PagarmeRecebedor pagarmeRecebedor;

	@Column(name = "CPF", nullable = false, length = 11)
	private String cpf;

	@Column(name = "SITE", nullable = true)
	private String site;

	@Column(name = "NOME_COMPLETO", nullable = false)
	private String nomeCompleto;

	@Column(name = "NOME_DA_MAE", nullable = true)
	private String nomeDaMae;

	@Column(name = "DT_NASC", nullable = false)
	private LocalDate dataNascimento;

	@ManyToOne
	@JoinColumn(name = "ID_OCUPACAO")
	private Ocupacao ocupacao;

	@Column(name = "RENDA_MENSAL", nullable = false)
	private BigDecimal rendaMensal;

	@ManyToOne
	@JoinColumn(name = "ID_BANCO")
	private Banco banco;

	@Column(name = "AGENCIA", nullable = false)
	private String agencia;

	@Column(name = "DV_AGENCIA", nullable = false, length = 2)
	private String dvAgencia;

	// verificar se vai ser o numero msm
	@Column(name = "CONTA", nullable = false)
	private String numeroConta;

	@Column(name = "DV_CONTA", nullable = false, length = 2)
	private String dvConta;

	@Column(name = "ENDERECO", nullable = false)
	private String endereco;

	@Column(name = "NUMERO", nullable = false)
	private String numero;

	@Column(name = "COMPLEMENTO", nullable = true)
	private String complemento;

	@Column(name = "BAIRRO", nullable = false)
	private String bairro;

	@Column(name = "CIDADE", nullable = false)
	private String cidade;

	@Column(name = "ESTADO", nullable = false)
	private String estado;

	@Column(name = "CEP", nullable = false, length = 8)
	private String cep;

	@Column(name = "PONTO_REFERENCIA", nullable = true)
	private String pontoReferencia;

}