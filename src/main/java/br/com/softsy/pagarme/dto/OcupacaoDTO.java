package br.com.softsy.pagarme.dto;



import br.com.softsy.pagarme.model.Ocupacao;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OcupacaoDTO {
	
	private Long idOcupacao;

	private String codCBO;

	private String ocupacao;
	
	
	public OcupacaoDTO(Ocupacao ocupacao) {
		this.idOcupacao = ocupacao.getIdOcupacao();
		this.codCBO = ocupacao.getCodCBO();
		this.ocupacao = ocupacao.getOcupacao();

	}

}
