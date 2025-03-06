package br.com.softsy.pagarme.service;

public interface EmailService {

	void sendEmail(Long idConta, String to, String subject, String message) throws Exception;
}