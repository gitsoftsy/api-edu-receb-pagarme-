package br.com.softsy.pagarme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_PROVEDOR_EMAIL_CONTA_OUTLOOK")
@Data
public class ProvedorEmailContaOutlook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEDOR_EMAIL_CONTA_OUTLOOK")
    private Long idProvedorEmailContaOutlook;

    @Column(name = "ID_CONTA", nullable = false)
    private Long idConta;

    @Column(name = "SMTP_HOST", nullable = false, length = 550)
    private String smtpHost;

    @Column(name = "SMTP_PORT", nullable = false, length = 5)
    private String smtpPort;

    @Column(name = "USERNAME", nullable = false, length = 550)
    private String username;

    @Column(name = "SENHA", nullable = false, length = 2500)
    private String senha;
}
