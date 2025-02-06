package br.com.softsy.pagarme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RecebedorLoginDTO {
	
	private Long idRecedebor;
	private String nome;
	private String documento;
	private String tabela;

	public RecebedorLoginDTO(RecebedorLoginDTO recebedor) {
		this.idRecedebor = recebedor.getIdRecedebor();
		this.nome = recebedor.getNome();
		this.documento = recebedor.getDocumento();
		this.tabela = recebedor.getTabela();
	}
}
