package com.broj.config;

import com.github.mxab.thymeleaf.extras.dataattribute.dialect.DataAttributeDialect;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Muztaba Hasanat
 * @author Rimon Mostafiz
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.broj")
public class WebConfig extends WebMvcConfigurerAdapter {


    public ServletContextTemplateResolver templateResolverDev() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setCacheable(false);
        setProperty(resolver);
        return resolver;
    }

    public void setProperty(ServletContextTemplateResolver resolver) {
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        Set<IDialect> dialects = new HashSet<>();
        dialects.add(new LayoutDialect());

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolverDev());
        engine.setAdditionalDialects(dialects);
        engine.addDialect(new DataAttributeDialect());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(1);
        resolver.setViewNames(new String[]{"*", "js/*", "template/*"});
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}

















