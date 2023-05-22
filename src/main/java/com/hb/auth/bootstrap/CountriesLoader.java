package com.hb.auth.bootstrap;

import com.hb.auth.model.postgres.Country;
import com.hb.auth.repository.CountryRepository;
import com.hb.auth.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CountriesLoader implements CommandLineRunner {
    private final CountryService countryService;
    private final CountryRepository countryRepository;
    @Override
    public void run(String... args){
        loadCountriesFromGraphqlAPI();
    }

    private void loadCountriesFromGraphqlAPI() {
        if(countryRepository.count() > 0) return;

        Mono<List<Country>> countries = countryService.getCountries();

        countries.subscribe(response -> countryRepository.saveAll(response.stream().filter(country -> !country.getCode().equals("IL")).collect(Collectors.toList())));
    }
}
