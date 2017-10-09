/**
 * 
 */
package com.frgz.tareas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * @author fabio
 *
 */
@Configuration
public class ThymeleafConfig {

	@Bean
	public TemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResover = new SpringResourceTemplateResolver();
		templateResover.setPrefix("/views/");
		templateResover.setSuffix(".html");
		templateResover.setTemplateMode("HTML5");
		templateResover.setCharacterEncoding("UTF-8");
		templateResover.setCacheable(false);
		templateResover.setOrder(1);

		return templateResover;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
}
