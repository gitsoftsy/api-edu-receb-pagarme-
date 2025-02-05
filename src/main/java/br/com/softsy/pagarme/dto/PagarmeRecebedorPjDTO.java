package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.softsy.pagarme.model.PagarmeRecebedorPj;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagarmeRecebedorPjDTO {
	
	private Long idPagarmeRecebedorPj;
    private Long idConta;
    private Long idPagarmeRecebedor;
    private String cnpj;
    private String nomeFantasia;
    private String razaoSocial;
    private String site;
    private Long idTipoEmpresa;
    private LocalDate dataFundacao;
    private BigDecimal receitaAnual;
    private Long idBanco;
    private String agencia;
    private String dvAgencia;
    private String contaBancaria;
    private String dvConta;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pontoReferencia;
    
    public PagarmeRecebedorPjDTO(PagarmeRecebedorPj pagarmeRecebedorPj) {
        this.idPagarmeRecebedorPj = pagarmeRecebedorPj.getIdPagarmeRecebedorPj();
        this.idConta = pagarmeRecebedorPj.getConta() != null ? pagarmeRecebedorPj.getConta().getIdConta() : null;
        this.idPagarmeRecebedor = pagarmeRecebedorPj.getPagarmeRecebedor() != null ? pagarmeRecebedorPj.getPagarmeRecebedor().getIdPagarmeRecebedor() : null;
        this.cnpj = pagarmeRecebedorPj.getCnpj();
        this.nomeFantasia = pagarmeRecebedorPj.getNomeFantasia();
        this.razaoSocial = pagarmeRecebedorPj.getRazaoSocial();
        this.site = pagarmeRecebedorPj.getSite();
        this.idTipoEmpresa = pagarmeRecebedorPj.getTipoEmpresa() != null ? pagarmeRecebedorPj.getTipoEmpresa().getIdTipoEmpresa() : null;
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
