package com.example.petstore.client;

import com.example.petstore.model.AnimalIds;
import com.example.petstore.model.AnimalResponseFromShelternet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Profile("!test")
public class ShelternetClientImpl implements ShelternetClient {

    @Value("${shelternet.url}")
    private String shelternetUrl;
    @Value("${shelternet.user}")
    private String shelternetUserName;
    @Value("${shelternet.password}")
    private String shelternetPassword;

    @Override
    public AnimalResponseFromShelternet requestAnimals(AnimalIds animalIds) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(
                new BasicAuthorizationInterceptor(shelternetUserName, shelternetPassword));
        List<String> ids = animalIds.getAnimalIds().stream().map(Object::toString).collect(Collectors.toList());
        HttpEntity<List<String>> request = new HttpEntity<>(ids);
        ParameterizedTypeReference<AnimalResponseFromShelternet> responseType = new ParameterizedTypeReference<>() {
        };
        return restTemplate.exchange(shelternetUrl + "/animals/request", HttpMethod.POST, request, responseType).getBody();
    }
}
