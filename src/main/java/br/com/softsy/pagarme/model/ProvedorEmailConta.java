package br.com.softsy.pagarme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_PROVEDOR_EMAIL_CONTA")
@Data
public class ProvedorEmailConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEDOR_EMAIL_CONTA")
    private Long idProvedorEmailConta;

    @Column(name = "ID_CONTA", nullable = false)
    private Long idConta;

    @Column(name = "ID_PROVEDOR_EMAIL", nullable = false)
    private Long idProvedorEmail;
}
