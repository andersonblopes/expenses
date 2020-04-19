package com.lopes.expenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

/**
 * The type Expenses application.
 */
@SpringBootApplication
public class ExpensesApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ExpensesApplication.class, args);
    }

    /**
     * Locale resolver locale resolver.
     *
     * @return the locale resolver
     */
    @Bean
    public LocaleResolver localeResolver() {
        return new FixedLocaleResolver(new Locale("pt", "BR"));
    }
}
