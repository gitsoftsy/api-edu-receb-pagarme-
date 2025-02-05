package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.softsy.pagarme.dto.PagarmeRecebedorDTO;
import br.com.softsy.pagarme.model.Ocupacao;
import br.com.softsy.pagarme.model.PagarmeRecebedorPF;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagarmeRecebedorPfDTO {

	private Long idPagarmeRecebedorPF;

	private Long idConta;

	private Long idPagarmeRecebedor;

	private String cpf;

	private String site;

	private String nomeCompleto;

	private String nomeDaMae;

	private LocalDate dataNascimento;

	private Long idOcupacao;

	private BigDecimal rendaMensal;

	private Long idBanco;

	private String agencia;

	private String dvAgencia;

	// verificar se vai ser o numero msm
	private String numeroConta;

	private String dvConta;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	private String pontoReferencia;

	public PagarmeRecebedorPfDTO(PagarmeRecebedorPF pagarmeRecebedorPf) {
	    this.idPagarmeRecebedorPF = pagarmeRecebedorPf.getIdPagarmeRecebedorPF();
	    this.idConta = (pagarmeRecebedorPf.getConta() != null) ? pagarmeRecebedorPf.getConta().getIdConta() : null;
	    this.idPagarmeRecebedor = (pagarmeRecebedorPf.getPagarmeRecebedor() != null) ? pagarmeRecebedorPf.getPagarmeRecebedor().getIdPagarmeRecebedor() : null;
	    this.cpf = pagarmeRecebedorPf.getCpf();
	    this.site = pagarmeRecebedorPf.getSite();
	    this.nomeCompleto = pagarmeRecebedorPf.getNomeCompleto();
	    this.nomeDaMae = pagarmeRecebedorPf.getNomeDaMae();
	    this.dataNascimento = pagarmeRecebedorPf.getDataNascimento();
	    this.idOcupacao = (pagarmeRecebedorPf.getOcupacao() != null) ? pagarmeRecebedorPf.getOcupacao().getIdOcupacao() : null;
	    this.rendaMensal = pagarmeRecebedorPf.getRendaMensal();
	    this.idBanco = (pagarmeRecebedorPf.getBanco() != null) ? pagarmeRecebedorPf.getBanco().getIdBanco() : null;
	    this.agencia = pagarmeRecebedorPf.getAgencia();
	    this.dvAgencia = pagarmeRecebedorPf.getDvAgencia();
	    this.numeroConta = pagarmeRecebedorPf.getNumeroConta();
	    this.dvConta = pagarmeRecebedorPf.getDvConta();
	    this.endereco = pagarmeRecebedorPf.getEndereco();
	    this.numero = pagarmeRecebedorPf.getNumero();
	    this.complemento = pagarmeRecebedorPf.getComplemento();
	    this.bairro = pagarmeRecebedorPf.getBairro();
	    this.cidade = pagarmeRecebedorPf.getCidade();
	    this.estado = pagarmeRecebedorPf.getEstado();
	    this.cep = pagarmeRecebedorPf.getCep();
	    this.pontoReferencia = pagarmeRecebedorPf.getPontoReferencia();
	}

}