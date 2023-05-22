package com.hb.auth;

import com.corundumstudio.socketio.SocketIOServer;
import com.hb.auth.model.Country;
import com.hb.auth.repository.CountryRepository;
import com.hb.auth.service.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
//@EnableConfigurationProperties(TwilioConfig.class)
@Slf4j
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	CommandLineRunner run(SocketIOServer server, CountryService countryService, CountryRepository countryRepository){
		return args -> {
			startSocketServer(server);

			consumeCountriesGraphQLAPI(countryService, countryRepository);
		};
	}

	private void startSocketServer(SocketIOServer server){
		server.start();
	}

	private void consumeCountriesGraphQLAPI(CountryService countryService, CountryRepository countryRepository){

		if(countryRepository.count() > 0) return;

		Mono<List<Country>> countries = countryService.getCountries();

		countries.subscribe(response -> countryRepository.saveAll(response.stream().filter(country -> !country.getCode().equals("IL")).collect(Collectors.toList())));
	}
}
