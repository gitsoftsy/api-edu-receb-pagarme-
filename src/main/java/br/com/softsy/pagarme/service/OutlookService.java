package br.com.softsy.pagarme.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softsy.pagarme.model.EmailAuthenticator;
import br.com.softsy.pagarme.model.ProvedorEmailConta;
import br.com.softsy.pagarme.model.ProvedorEmailContaOutlook;
import br.com.softsy.pagarme.repository.ProvedorEmailContaOutlookRepository;
import br.com.softsy.pagarme.repository.ProvedorEmailContaRepository;

@Service
public class OutlookService implements EmailService {

    @Autowired
    private ProvedorEmailContaRepository provedorEmailContaRepository;
    
    @Autowired
    private ProvedorEmailContaOutlookRepository provedorEmailContaOutlookRepository;
    
    @Override
    public void sendEmail(Long idConta, String to, String subject, String nomeConta, String linkCadastro) throws Exception {
        ProvedorEmailConta provedorEmailConta = provedorEmailContaRepository.findByIdContaAndIdProvedorEmail(idConta, 1L);
        if (provedorEmailConta == null) {
            throw new Exception("Configuração do provedor de e-mail não encontrada ou não configurada para Outlook.");
        }

        ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(idConta);
        if (provedorEmailContaOutlook == null) {
            throw new Exception("Configuração do Outlook não encontrada para esta conta.");
        }

        Properties props = new Properties();
        props.put("mail.smtp.host", provedorEmailContaOutlook.getSmtpHost());
        props.put("mail.smtp.port", provedorEmailContaOutlook.getSmtpPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        EmailAuthenticator authenticator = new EmailAuthenticator(provedorEmailContaOutlook.getUsername(), "#@Sum@re#@");
        Session session = Session.getInstance(props, authenticator);

        // 🔄 Carregar template HTML do classpath
        String templatePath = "/templates/email-confirmacao.html"; // Caminho relativo dentro do classpath
        InputStream inputStream = getClass().getResourceAsStream(templatePath);
        if (inputStream == null) {
            throw new Exception("Template de e-mail não encontrado: " + templatePath);
        }
        
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        htmlContent = htmlContent.replace("{{nomeConta}}", nomeConta);
        htmlContent = htmlContent.replace("{{linkCadastro}}", linkCadastro);

        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(provedorEmailContaOutlook.getUsername()));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(htmlContent, "text/html; charset=UTF-8");

        Transport.send(mimeMessage);
        System.out.println("Outlook: E-mail enviado para " + to);
    }


	@Override
	public void sendEmail(Long idConta, String to, String subject, String message) throws Exception {
	
	}
	
	@Override
	public void sendEmailPj(Long idConta, String to, String subject, String nomeUsuario, String linkCadastro, String nomeParceiro) throws Exception {
	    ProvedorEmailConta provedorEmailConta = provedorEmailContaRepository.findByIdContaAndIdProvedorEmail(idConta, 1L);
	    if (provedorEmailConta == null) {
	        throw new Exception("Configuração do provedor de e-mail não encontrada ou não configurada para Outlook.");
	    }

	    ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(idConta);
	    if (provedorEmailContaOutlook == null) {
	        throw new Exception("Configuração do Outlook não encontrada para esta conta.");
	    }

	    Properties props = new Properties();
	    props.put("mail.smtp.host", provedorEmailContaOutlook.getSmtpHost());
	    props.put("mail.smtp.port", provedorEmailContaOutlook.getSmtpPort());
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

	    EmailAuthenticator authenticator = new EmailAuthenticator(provedorEmailContaOutlook.getUsername(), "#@Sum@re#@");
	    Session session = Session.getInstance(props, authenticator);

	    // 🔄 Carregar template HTML corretamente
	    String templatePath = "/templates/email-completo-usuario.html";
	    InputStream inputStream = getClass().getResourceAsStream(templatePath);
	    if (inputStream == null) {
	        throw new Exception("Template de e-mail não encontrado: " + templatePath);
	    }

	    String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
	    htmlContent = htmlContent.replace("{{nomeUsuario}}", nomeUsuario);
	    htmlContent = htmlContent.replace("{{nomeParceiro}}", nomeParceiro);
	    htmlContent = htmlContent.replace("{{linkCadastro}}", linkCadastro);

	    Message mimeMessage = new MimeMessage(session);
	    mimeMessage.setFrom(new InternetAddress(provedorEmailContaOutlook.getUsername()));
	    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    mimeMessage.setSubject(subject);
	    mimeMessage.setContent(htmlContent, "text/html; charset=UTF-8");

	    Transport.send(mimeMessage);
	    System.out.println("Outlook: E-mail enviado para " + to);
	}
    
	@Override
	public void sendEmailPjParceiro(Long idConta, String to, String subject, String linkPlataforma) throws Exception {
	    ProvedorEmailConta provedorEmailConta = provedorEmailContaRepository.findByIdContaAndIdProvedorEmail(idConta, 1L);
	    if (provedorEmailConta == null) {
	        throw new Exception("Configuração do provedor de e-mail não encontrada ou não configurada para Outlook.");
	    }

	    ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(idConta);
	    if (provedorEmailContaOutlook == null) {
	        throw new Exception("Configuração do Outlook não encontrada para esta conta.");
	    }

	    Properties props = new Properties();
	    props.put("mail.smtp.host", provedorEmailContaOutlook.getSmtpHost());
	    props.put("mail.smtp.port", provedorEmailContaOutlook.getSmtpPort());
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");

	    EmailAuthenticator authenticator = new EmailAuthenticator(provedorEmailContaOutlook.getUsername(), "#@Sum@re#@");
	    Session session = Session.getInstance(props, authenticator);

	    // 🔄 Carregar template HTML corretamente
	    String templatePath = "/templates/email-completo-parceiro.html";
	    InputStream inputStream = getClass().getResourceAsStream(templatePath);
	    if (inputStream == null) {
	        throw new Exception("Template de e-mail não encontrado: " + templatePath);
	    }

	    String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
	    htmlContent = htmlContent.replace("{{linkPlataforma}}", linkPlataforma);

	    Message mimeMessage = new MimeMessage(session);
	    mimeMessage.setFrom(new InternetAddress(provedorEmailContaOutlook.getUsername()));
	    mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	    mimeMessage.setSubject(subject);
	    mimeMessage.setContent(htmlContent, "text/html; charset=UTF-8");

	    Transport.send(mimeMessage);
	    System.out.println("Outlook: E-mail enviado para " + to);
	}



}
