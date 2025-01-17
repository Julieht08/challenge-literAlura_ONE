package com.julieht.challenge_literAlura_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.julieht.challenge_literAlura_one.principal.Principal;
import com.julieht.challenge_literAlura_one.repositorio.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class ChallengeLiterAluraOneApplication implements CommandLineRunner {

	@Autowired
	private ILibrosRepository libroRepository;
	
	@Autowired
	private IAutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraOneApplication.class, args);
	}

	@Override
	public void run(String... args) {

		try {
			System.out.print("\033[H\033[2J");
			System.out.flush();

			Principal principal = new Principal(libroRepository, autorRepository);
			principal.muestraElMenu();

		} catch (Exception e) {
			System.out.println("Ocurrió un error al ejecutar la aplicación: " + e.getMessage());
			e.printStackTrace(); // Se agrega para mejor diagnóstico
		}
	}
}
