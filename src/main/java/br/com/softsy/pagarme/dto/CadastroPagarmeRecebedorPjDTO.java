package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastroPagarmeRecebedorPjDTO {

	private Long idPagarmeRecebedorPj;
	@NotNull
	private Long idConta;
	@NotNull
	private Long idPagarmeRecebedor;
	@NotNull
	private String cnpj;
	@NotNull
	private String nomeFantasia;
	@NotNull
	private String razaoSocial;
	private String site;
	@NotNull
	private Long idTipoEmpresa;
	@NotNull
	private LocalDate dataFundacao;
	@NotNull
	private BigDecimal receitaAnual;
	@NotNull
	private Long idBanco;
	@NotNull
	private String agencia;
	@NotNull
	private String dvAgencia;
	@NotNull
	private String contaBancaria;
	@NotNull
	private String dvConta;
	@NotNull
	private String endereco;
	@NotNull
	private String numero;
	private String complemento;
	@NotNull
	private String bairro;
	@NotNull
	private String cidade;
	@NotNull
	private String estado;
	@NotNull
	private String cep;
	private String pontoReferencia;

	public CadastroPagarmeRecebedorPjDTO(PagarmeRecebedorPj pagarmeRecebedorPj) {
		this.idPagarmeRecebedorPj = pagarmeRecebedorPj.getIdPagarmeRecebedorPj();
		this.idConta = pagarmeRecebedorPj.getConta() != null ? pagarmeRecebedorPj.getConta().getIdConta() : null;
		this.idPagarmeRecebedor = pagarmeRecebedorPj.getPagarmeRecebedor() != null
				? pagarmeRecebedorPj.getPagarmeRecebedor().getIdPagarmeRecebedor()
				: null;
		this.cnpj = pagarmeRecebedorPj.getCnpj();
		this.nomeFantasia = pagarmeRecebedorPj.getNomeFantasia();
		this.razaoSocial = pagarmeRecebedorPj.getRazaoSocial();
		this.site = pagarmeRecebedorPj.getSite();
		this.idTipoEmpresa = pagarmeRecebedorPj.getTipoEmpresa() != null
				? pagarmeRecebedorPj.getTipoEmpresa().getIdTipoEmpresa()
				: null;
		this.dataFundacao = pagarmeRecebedorPj.getDataFundacao();
		this.receitaAnual = pagarmeRecebedorPj.getReceitaAnual();
		this.idBanco = pagarmeRecebedorPj.getBanco() != null ? pagarmeRecebedorPj.getBanco().getIdBanco() : null;
		this.agencia = pagarmeRecebedorPj.getAgencia();
		this.dvAgencia = pagarmeRecebedorPj.getDvAgencia();
		this.contaBancaria = pagarmeRecebedorPj.getContaBancaria();
		this.dvConta = pagarmeRecebedorPj.getDvConta();
		this.endereco = pagarmeRecebedorPj.getEndereco();
		this.numero = pagarmeRecebedorPj.getNumero();
		this.complemento = pagarmeRecebedorPj.getComplemento();
		this.bairro = pagarmeRecebedorPj.getBairro();
		this.cidade = pagarmeRecebedorPj.getCidade();
		this.estado = pagarmeRecebedorPj.getEstado();
		this.cep = pagarmeRecebedorPj.getCep();
		this.pontoReferencia = pagarmeRecebedorPj.getPontoReferencia();
	}
}
