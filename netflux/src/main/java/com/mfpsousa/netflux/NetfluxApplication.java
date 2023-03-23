package com.mfpsousa.netflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
@SpringBootApplication
public class NetfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetfluxApplication.class, args);
	}

}
