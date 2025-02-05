package br.com.softsy.pagarme.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "TBL_PAGARME_RECEBEDOR_PJ")
@Data
public class PagarmeRecebedorPj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGARME_RECEBEDOR_PJ")
    private Long idPagarmeRecebedorPj;

    @ManyToOne
    @JoinColumn(name = "ID_CONTA")
    private Conta conta;

    @ManyToOne
    @JoinColumn(name = "ID_PAGARME_RECEBEDOR")
    private PagarmeRecebedor pagarmeRecebedor;

    @Column(name = "CNPJ", length = 14, nullable = false)
    private String cnpj;

    @Column(name = "NOME_FANTASIA", length = 255, nullable = false)
    private String nomeFantasia;

    @Column(name = "RAZAO_SOCIAL", length = 255, nullable = false)
    private String razaoSocial;

    @Column(name = "SITE", length = 255)
    private String site;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_EMPRESA")
    private TipoEmpresa tipoEmpresa;

    @Column(name = "DT_FUNDACAO")
    private LocalDate dataFundacao;

    @Column(name = "RECEITA_ANUAL", precision = 15, scale = 2)
    private BigDecimal receitaAnual;

    @ManyToOne
    @JoinColumn(name = "ID_BANCO")
    private Banco banco;

    @Column(name = "AGENCIA", length = 20)
    private String agencia;

    @Column(name = "DV_AGENCIA", length = 2)
    private String dvAgencia;

    @Column(name = "CONTA", length = 20)
    private String contaBancaria;

    @Column(name = "DV_CONTA", length = 2)
    private String dvConta;

    @Column(name = "ENDERECO", length = 255)
    private String endereco;

    @Column(name = "NUMERO", length = 10)
    private String numero;

    @Column(name = "COMPLEMENTO", length = 255)
    private String complemento;

    @Column(name = "BAIRRO", length = 255)
    private String bairro;

    @Column(name = "CIDADE", length = 255)
    private String cidade;

    @Column(name = "ESTADO", length = 2)
    private String estado;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "PONTO_REFERENCIA", length = 255)
    private String pontoReferencia;
}
