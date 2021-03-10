package com.test.tarificador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @author Miguel Angel Camacho Campos
 * @correo desarolloti22_lem@castores.com.mx
 * @fecha 02 feb. 2021
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
