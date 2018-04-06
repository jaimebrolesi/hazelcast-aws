package com.jaimebrolesi.hazelcastclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application class used to start the Hazelcast like a client.
 *
 * @author Jaime Brolesi
 * Creation date: 05/04/2018
 */
@SpringBootApplication
public class HazelcastClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClientApplication.class, args);
	}
}
