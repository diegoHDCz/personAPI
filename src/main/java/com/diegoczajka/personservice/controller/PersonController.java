package com.diegoczajka.personservice.controller;

import com.diegoczajka.personservice.model.Person.PersonDetailData;
import com.diegoczajka.personservice.model.Person.PersonRegisterData;
import com.diegoczajka.personservice.model.Person.UpdatePersonData;
import com.diegoczajka.personservice.service.PersonService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService service;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PersonRegisterData data, UriComponentsBuilder uriBuilder) {
        var result = service.registerPerson(data);

        var uri = uriBuilder.path("/person/{id}").buildAndExpand(result.id()).toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePersonData data) {
        var result = service.editPerson(data);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    @Transactional
    public ResponseEntity list(@PageableDefault(size = 10, sort = {"id"}) Pageable pagination) throws Exception {
        var page = service.listPerson(pagination);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDetailData> getOne(Long id) {
        var pesron = service.findOne(id);
        return ResponseEntity.ok(pesron);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        service.deletePerson(id);
        return ResponseEntity.noContent().build();
    }


}
