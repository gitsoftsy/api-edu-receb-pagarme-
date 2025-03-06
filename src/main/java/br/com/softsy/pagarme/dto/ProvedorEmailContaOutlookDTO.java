package br.com.softsy.pagarme.dto;

import br.com.softsy.pagarme.model.ProvedorEmailContaOutlook;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvedorEmailContaOutlookDTO {

    private Long idProvedorEmailContaOutlook;
    private Long idConta;
    private String smtpHost;
    private String smtpPort;
    private String username;
    private String senha;

    public ProvedorEmailContaOutlookDTO(ProvedorEmailContaOutlook provedorEmailContaOutlook) {
        this.idProvedorEmailContaOutlook = provedorEmailContaOutlook.getIdProvedorEmailContaOutlook();
        this.idConta = provedorEmailContaOutlook.getIdConta();
        this.smtpHost = provedorEmailContaOutlook.getSmtpHost();
        this.smtpPort = provedorEmailContaOutlook.getSmtpPort();
        this.username = provedorEmailContaOutlook.getUsername();
        this.senha = provedorEmailContaOutlook.getSenha();
    }
}
