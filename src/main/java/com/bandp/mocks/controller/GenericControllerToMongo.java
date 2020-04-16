package com.bandp.mocks.controller;

import com.bandp.mocks.model.GenericObject;
import com.bandp.mocks.service.GenericObjectService;
import java.util.List;
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
  GenericObjectService genericObjectService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<GenericObject> getGenericObject() {
    log.info("Getting all");
    return genericObjectService.getAllGenericObjects();
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public GenericObject getGenericObject(@PathVariable("id") ObjectId id) {
    log.info("Getting one: ", id);
    return genericObjectService.getGenericObject(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GenericObject createGenericObject(@Valid @RequestBody GenericObject genericObject) {
    log.info("creating one...");
    return genericObjectService.createGenericObject(genericObject);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGenericObject(@PathVariable("id") ObjectId id) {
    log.info("Deleting one: ", id);
    genericObjectService.deleteGenericObject(id);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public GenericObject updateUser(@RequestBody GenericObject genericObject,
      @PathVariable ObjectId id) {
    log.info("Updating one: ", id);
    return genericObjectService.updateGenericObject(genericObject, id);
  }

}
