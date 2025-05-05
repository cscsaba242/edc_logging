package com.segittur.auditing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This middle tier back-end application is one part of an SEGITTUR project eco-system.
 * Where the main goal is auditing { @linkplain https://github.com/eclipse-tractusx/tractusx-edc Tractus-X EDC }
 * main processes in
 * { @linkplain  https://eclipse-edc.github.io/documentation/for-adopters/control-plane control-plane part }
 * through web-api interfaces for
 * - Tractus-X EDC Connector (upstream)
 * - client side (downstream)
 */
@SpringBootApplication
public class AuditingApplication {
    /**
     * The main method which serves as the entry point for the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application at runtime.
     */
    public static void main(String[] args) {

        SpringApplication.run(AuditingApplication.class, args);
    }

}
