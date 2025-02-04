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

import br.com.softsy.pagarme.model.Conta;
import br.com.softsy.pagarme.model.Usuario;
import lombok.Data;

@Entity
@Table(name = "TBL_PAGARME_RECEBEDOR")
@Data
public class PagarmeRecebedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PAGARME_RECEBEDOR")
	private Long idPagarmeRecebedor;

	@ManyToOne
	@JoinColumn(name = "ID_CONTA")
	private Conta conta;

	@Column(name = "DT_CADASTRO", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", nullable = false)
	private LocalDateTime dataCadastro;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@Column(name = "TIPO_PESSOA", length = 2, nullable = false)
	private String tipoPessoa;

	@Column(name = "ATIVO", length = 1, nullable = false)
	private Character ativo;

	@Column(name = "E_MAIL", nullable = false)
	private String email;

	@Column(name = "SENHA", nullable = false)
	private String senha;

	@Column(name = "TRANSF_AUTOMATICA", length = 1, nullable = false)
	private Character transfAutomatica;

	@Column(name = "TRANSF_INTERVALO", length = 1, nullable = false)
	private Character transfIntervalo;

	@Column(name = "TRANSF_DIA", length = 11, nullable = false)
	private Integer transfDia;

	@Column(name = "ANTECIP_AUT", length = 1, nullable = false)
	private Character antecipAut;

	@Column(name = "ANTECIP_TP", length = 1)
	private Character antecipTp;

	@Column(name = "ANTECIP_VOLUME", length = 11)
	private Integer antecipVolume;

	@Column(name = "ANTECIP_DIAS", length = 11)
	private Integer antecipDias;

	@Column(name = "ANTECIP_DELAY", length = 15)
	private String antecipDelay;

	@Column(name = "CD_PAGARME_RECEBEDOR")
	private String cdPagarmeRecebedor;

	@Column(name = "TELEFONE")
	private String telefone;

	@Column(name = "CELULAR")
	private String celular;

}