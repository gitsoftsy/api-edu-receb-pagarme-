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
import lombok.Data;

@Entity
@Table(name = "TBL_RECEBEDOR_TEMP")
@Data
public class RecebedorTemp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_RECEBEDOR_TEMP")
	private Long idRecebedorTemp;

	@ManyToOne
	@JoinColumn(name = "ID_CONTA")
	private Conta conta;

	@Column(name = "DT_CADASTRO", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime dataCadastro;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@Column(name = "TIPO_PESSOA", unique = true, length = 2)
	private Character tipoPessoa;

	@Column(name = "NOME", unique = true)
	private String nome;

	@Column(name = "DOCUMENTO", nullable = false, length = 14)
	private String documento;

	@Column(name = "E_MAIL", nullable = false)
	private String email;

	@Column(name = "SENHA", length = 2550)
	private String senha;

	@Column(name = "TRANSF_AUTOMATICA", length = 1)
	private Character transfAutomatica;

	@Column(name = "TRANSF_INTERVALO", length = 1)
	private Character transfIntervalo;

	@Column(name = "TRANSF_DIA", length = 11)
	private Integer transfDia;

	@Column(name = "ANTECIP_AUT", length = 1)
	private Character antecipAut;

	@Column(name = "ANTECIP_TP", length = 1)
	private Character antecipTp;

	@Column(name = "ANTECIP_VOLUME", length = 11)
	private Integer antecipVolume;

	@Column(name = "ANTECIP_DIAS", length = 11)
	private Integer antecipDias;

	@Column(name = "ANTECIP_DELAY", length = 15)
	private String antecipDelay;

}
