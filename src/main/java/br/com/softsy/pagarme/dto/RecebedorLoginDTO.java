package br.com.softsy.pagarme.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RecebedorLoginDTO {

	private Long idRecebedor;
	private String nome;
	private String documento;
	private String tabela;
	private Long idConta;

	public RecebedorLoginDTO(RecebedorLoginDTO recebedor) {
		this.idRecebedor = recebedor.getIdRecebedor();
		this.nome = recebedor.getNome();
		this.documento = recebedor.getDocumento();
		this.tabela = recebedor.getTabela();
		this.idConta = recebedor.getIdConta();
	}
}
