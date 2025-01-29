package br.com.softsy.pagarme.dto;

import br.com.softsy.pagarme.model.Banco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BancoDTO {

	private Long idBanco;

	private String codigo;

	private String banco;

	public BancoDTO(Banco banco) {
		this.idBanco = banco.getIdBanco();
		this.codigo = banco.getCodigo();
		this.banco = banco.getBanco();

	}

}
