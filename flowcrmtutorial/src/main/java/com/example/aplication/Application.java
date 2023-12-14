package com.example.aplication;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "flowcrmtutorial",variant=Lumo.DARK)



public class Application implements AppShellConfigurator {

    private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
    	
    	//
    	
        SpringApplication.run(Application.class, args);
    }

}
