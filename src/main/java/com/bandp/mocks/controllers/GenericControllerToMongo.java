package com.bandp.mocks.controllers;

import com.bandp.mocks.exceptions.GenericObjectNotFoundException;
import com.bandp.mocks.models.GenericObject;
import com.bandp.mocks.repositories.GenericRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("api/v1/genericToMongo")
public class GenericControllerToMongo {

  @Autowired
  private GenericRepository repository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<GenericObject> getGenericObject() {
    log.info("Getting all");
    return repository.findAll();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GenericObject getGenericObject(@PathVariable("id") ObjectId id) {
    log.info("Getting one: ", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }
    return genericObjectOptional.get();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GenericObject createGenericObject(@Valid @RequestBody GenericObject genericObject) {
    log.info("creating one...");
    genericObject.set_Id(ObjectId.get());
    repository.save(genericObject);
    return genericObject;
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGenericObject(@PathVariable("id") ObjectId id) {
    log.info("Deleting one: ", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }

    repository.delete(genericObjectOptional.get());
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public GenericObject updateUser(@RequestBody GenericObject genericObject,
      @PathVariable ObjectId id) {
    log.info("Updating one: ", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }

    genericObject.set_Id(id);
    repository.save(genericObject);
    return genericObject;
  }

}
