package br.com.softsy.pagarme.dto;

import br.com.softsy.pagarme.model.TipoEmpresa;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipoEmpresaDTO {

	private Long idTipoEmpresa;

	private String tipoEmpresa;

	private String descricao;

	public TipoEmpresaDTO(TipoEmpresa tipoempresa) {
		this.idTipoEmpresa = tipoempresa.getIdTipoEmpresa();
		this.tipoEmpresa = tipoempresa.getTipoEmpresa();
		this.descricao = tipoempresa.getDescricao();

	}

}
