package br.com.softsy.pagarme.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.softsy.pagarme.model.PagarmeRecebedorPjRespLegal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagarmeRecebedorPjRespLegalDTO {

    private Long idPagarmeRecebedorPjRespLegal;
    private Long idPagarmeRecebedor;
    private String nomeRespLegal;
    private String emailRespLegal;
    private String cpfRespLegal;
    private LocalDate dataNascimentoRespLegal;
    private String nomeMaeRespLegal;
    private Long idOcupacao;
    private BigDecimal rendaMensal;
    private String enderecoRespLegal;
    private String numeroRespLegal;
    private String complementoRespLegal;
    private String bairroRespLegal;
    private String cidadeRespLegal;
    private String estadoRespLegal;
    private String cepRespLegal;
    private String pontoReferenciaRespLegal;
    private String telefoneRespLegal;
    private String celularRespLegal;

    public PagarmeRecebedorPjRespLegalDTO(PagarmeRecebedorPjRespLegal pagarmeRecebedorPjRespLegal) {
        this.idPagarmeRecebedorPjRespLegal = pagarmeRecebedorPjRespLegal.getIdPagarmeRecebedorPjRespLegal();
        this.idPagarmeRecebedor = pagarmeRecebedorPjRespLegal.getPagarmeRecebedor().getIdPagarmeRecebedor();
        this.nomeRespLegal = pagarmeRecebedorPjRespLegal.getNomeRespLegal();
        this.emailRespLegal = pagarmeRecebedorPjRespLegal.getEmailRespLegal();
        this.cpfRespLegal = pagarmeRecebedorPjRespLegal.getCpfRespLegal();
        this.dataNascimentoRespLegal = pagarmeRecebedorPjRespLegal.getDtNascRespLegal();
        this.nomeMaeRespLegal = pagarmeRecebedorPjRespLegal.getNomeMaeRespLegal();
        this.idOcupacao = pagarmeRecebedorPjRespLegal.getOcupacao() != null ? pagarmeRecebedorPjRespLegal.getOcupacao().getIdOcupacao() : null;
        this.rendaMensal = pagarmeRecebedorPjRespLegal.getRendaMensal();
        this.enderecoRespLegal = pagarmeRecebedorPjRespLegal.getEnderecoRespLegal();
        this.numeroRespLegal = pagarmeRecebedorPjRespLegal.getNumeroRespLegal();
        this.complementoRespLegal = pagarmeRecebedorPjRespLegal.getComplementoRespLegal();
        this.bairroRespLegal = pagarmeRecebedorPjRespLegal.getBairroRespLegal();
        this.cidadeRespLegal = pagarmeRecebedorPjRespLegal.getCidadeRespLegal();
        this.estadoRespLegal = pagarmeRecebedorPjRespLegal.getEstadoRespLegal();
        this.cepRespLegal = pagarmeRecebedorPjRespLegal.getCepRespLegal();
        this.pontoReferenciaRespLegal = pagarmeRecebedorPjRespLegal.getPontoReferenciaRespLegal();
        this.telefoneRespLegal = pagarmeRecebedorPjRespLegal.getTelefoneRespLegal();
        this.celularRespLegal = pagarmeRecebedorPjRespLegal.getCelularRespLegal();
    }
}