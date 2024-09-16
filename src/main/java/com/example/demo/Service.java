package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

@org.springframework.stereotype.Service
@Slf4j
public class Service {

    @CachePut(cacheNames = "cacheStore", key = "#name")
    public Person generatePerson(String name) {
        Person person = new Person(UUID.randomUUID().toString(), name, "Switzerland");
        log.info("Generated Person: {}", person);
        return person;
    }

    @Cacheable(cacheNames = "cacheStore", key = "#person.name")
    public Person fetchPerson(Person person) {
        log.info("Person request received = {}", person);
        return person;
    }

    @CacheEvict(cacheNames = "cacheStore", key = "#person.name")
    public void evictPerson(Person person) {
        log.info("evicting Person = {}", person);
    }


}
