package com.example.isa.event.repository;

import com.example.isa.entity.Family;
import com.example.isa.event.dto.PostFamilyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class FamilyEventRepository {

    private final RestTemplate restTemplate;

    @Autowired
    public FamilyEventRepository(@Value("${species.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Family family) {
        restTemplate.delete("/families/{id}", family.getId());
    }

    public void create(Family family) {
        restTemplate.postForLocation("/families", PostFamilyRequest.entityToDtoMapper().apply(family));
    }

}
