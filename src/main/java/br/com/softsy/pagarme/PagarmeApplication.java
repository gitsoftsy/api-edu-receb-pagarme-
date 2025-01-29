package br.com.softsy.pagarme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class PagarmeApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(PagarmeApplication.class, args);
	}

}
