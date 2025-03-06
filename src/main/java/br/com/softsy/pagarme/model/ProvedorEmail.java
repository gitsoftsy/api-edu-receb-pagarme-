package br.com.softsy.pagarme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_PROVEDOR_EMAIL")
@Data
public class ProvedorEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROVEDOR_EMAIL")
    private Long idProvedorEmail;
    
    @Column(name = "PROVEDOR_EMAIL", nullable = false, unique = true, length = 30)
    private String provedorEmail;

}
