package com.bandp.mocks.controllers;

import com.bandp.mocks.exceptions.GenericObjectNotFoundException;
import com.bandp.mocks.models.GenericObject;
import com.bandp.mocks.repositories.GenericRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/genericToMongo")
public class GenericControllerToMongo {

    @Autowired
    private GenericRepository repository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenericObject> getGenericObject() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GenericObject getGenericObject(@PathVariable("id") ObjectId id) {
        Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

        if (!genericObjectOptional.isPresent()) {
            throw new GenericObjectNotFoundException("id-" + id);
        }
        return genericObjectOptional.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericObject createGenericObject(@Valid @RequestBody GenericObject genericObject) {
        genericObject.set_Id(ObjectId.get());
        repository.save(genericObject);
        return genericObject;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGenericObject(@PathVariable("id") ObjectId id) {
        Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

        if (!genericObjectOptional.isPresent()) {
            throw new GenericObjectNotFoundException("id-" + id);
        }

        repository.delete(genericObjectOptional.get());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public GenericObject updateUser(@RequestBody GenericObject genericObject, @PathVariable ObjectId id) {
        Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

        if (!genericObjectOptional.isPresent()) {
            throw new GenericObjectNotFoundException("id-" + id);
        }

        genericObject.set_Id(id);
        repository.save(genericObject);
        return genericObject;
    }

}
