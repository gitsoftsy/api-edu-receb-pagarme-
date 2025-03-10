package br.com.softsy.pagarme.service;

public interface EmailService {

	void sendEmail(Long idConta, String to, String subject, String message) throws Exception;

	void sendEmail(Long idConta, String to, String subject, String nomeConta, String linkCadastro) throws Exception;
}