package br.com.softsy.pagarme.dto;

import javax.validation.constraints.NotNull;

import br.com.softsy.pagarme.model.ProvedorEmail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProvedorEmailDTO {

    private Long idProvedorEmail;

    @NotNull
    private String provedorEmail;

    public ProvedorEmailDTO(ProvedorEmail provedorEmail) {
        this.idProvedorEmail = provedorEmail.getIdProvedorEmail();
        this.provedorEmail = provedorEmail.getProvedorEmail();
    }
}
