package com.diegoczajka.personservice.controller;

import com.diegoczajka.personservice.model.Person.PersonDetailData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.service.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PersonController {

    @Autowired
    PersonService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PersonRegisterData data, UriComponentsBuilder uriBuilder) {
        var result = service.registerPerson(data);

        var uri = uriBuilder.path("/{id}").buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping
    @Transactional
    public ResponseEntity list(@PageableDefault(size = 10, sort = {"id"}) Pageable pagination) {
        var page = service.listPerson(pagination);
        return ResponseEntity.ok(page);

    }

    @GetMapping("{/id}")
    public ResponseEntity<PersonDetailData> getOne(Long id) {
        var pesron = service.findOne(id);
        return ResponseEntity.ok(pesron);
    }

    @DeleteMapping("{/id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }


}
