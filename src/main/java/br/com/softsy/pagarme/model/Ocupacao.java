package br.com.softsy.pagarme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_OCUPACAO")
@Data
public class Ocupacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_OCUPACAO")
	private Long idOcupacao;

	@Column(name = "COD_CBO", nullable = false)
	private String codCBO;

	@Column(name = "OCUPACAO", nullable = false)
	private String ocupacao;

}
