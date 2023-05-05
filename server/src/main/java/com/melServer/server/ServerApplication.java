package com.melServer.server;

import com.melServer.server.enumeration.Status;
import com.melServer.server.model.Server;
import com.melServer.server.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository serverRepository){
		return args -> {
			serverRepository.save(new Server(null, "192.168.1.160","Ubuntu Linux", "16 GB", "Personal PC",
					"http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.58","MS 2000", "16 GB", "Web server",
					"http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.21","Red Hat", "64 GB", "Personal PC",
					"http://localhost:8080/server/image/server.png", Status.SERVER_UP));
			serverRepository.save(new Server(null, "192.168.1.14","Enterprise", "16 GB", "Mail Server",
					"http://localhost:8080/server/image/server.png", Status.SERVER_DOWN));
		};
	}

}
