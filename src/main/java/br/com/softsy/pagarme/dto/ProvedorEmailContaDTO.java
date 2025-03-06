package br.com.softsy.pagarme.dto;

import br.com.softsy.pagarme.model.ProvedorEmailConta;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvedorEmailContaDTO {

    private Long idProvedorEmailConta;
    private Long idConta;
    private Long idProvedorEmail;

    public ProvedorEmailContaDTO(ProvedorEmailConta provedorEmailConta) {
        this.idProvedorEmailConta = provedorEmailConta.getIdProvedorEmailConta();
        this.idConta = provedorEmailConta.getIdConta();
        this.idProvedorEmail = provedorEmailConta.getIdProvedorEmail();
    }
}
	