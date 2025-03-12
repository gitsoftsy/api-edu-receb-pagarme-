package br.com.softsy.pagarme.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(2L);
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

        // 🔄 Carregar template HTML
        String templatePath = "src/main/resources/templates/email-confirmacao.html";
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);
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

        ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(2L);
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

        // 🔄 Carregar template HTML
        String templatePath = "src/main/resources/templates/email-completo-usuario.html";
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);
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

        ProvedorEmailContaOutlook provedorEmailContaOutlook = provedorEmailContaOutlookRepository.findByIdConta(2L);
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

        // 🔄 Carregar template HTML
        String templatePath = "src/main/resources/templates/email-completo-parceiro.html";
        String htmlContent = new String(Files.readAllBytes(Paths.get(templatePath)), StandardCharsets.UTF_8);
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
