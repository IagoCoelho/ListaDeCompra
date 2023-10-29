package br.com.fiap.listacompra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class ListaCompraApplication implements WebMvcConfigurer{

	private final LocaleChangeInterceptor localInterceptor;

	public ListaCompraApplication(LocaleChangeInterceptor localInterceptor) {
		this.localInterceptor = localInterceptor;
	}

	public static void main(String[] args) {
		SpringApplication.run(ListaCompraApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localInterceptor);
	}

}
