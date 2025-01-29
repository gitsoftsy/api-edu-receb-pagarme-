package br.com.softsy.pagarme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_TIPO_EMPRESA")
@Data
public class TipoEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TIPO_EMPRESA")
	private Long idTipoEmpresa;

	@Column(name = "TIPO_EMPRESA", unique = true)
	private String tipoEmpresa;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

}
