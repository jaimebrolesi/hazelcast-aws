package com.jaimebrolesi.hazelcastserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class used to start the Hazelcast like a server.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@SpringBootApplication
public class HazelcastServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastServerApplication.class, args);
	}
}
