package com.example.server_program;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerProgramApplication implements CommandLineRunner {

	@Autowired
	private Flyway flyway;

	public static void main(String[] args) {

		SpringApplication.run(ServerProgramApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{
		flyway.migrate();
	}

}
