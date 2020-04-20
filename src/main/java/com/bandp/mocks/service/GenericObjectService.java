package com.bandp.mocks.service;

import com.bandp.mocks.exception.GenericObjectNotFoundException;
import com.bandp.mocks.model.GenericObject;
import com.bandp.mocks.repository.GenericRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component(value = "genericObjectService")
public class GenericObjectService {

  @Autowired
  private GenericRepository repository;

  public List<GenericObject> getAllGenericObjects() {
    log.info("Getting all GenericObjects (service layer)");
    return repository.findAll();
  }

  public GenericObject getGenericObject(ObjectId id) {
    log.info("Getting a GenericObject(service layer): %s", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }
    return genericObjectOptional.get();
  }

  public GenericObject createGenericObject(GenericObject genericObject) {
    log.info("Creating a GenericObject (service layer)");
    genericObject.set_Id(ObjectId.get());
    repository.save(genericObject);
    return genericObject;
  }

  public void deleteGenericObject(ObjectId id) {
    log.info("Deleting a GenericObject (service layer): %s", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }

    repository.delete(genericObjectOptional.get());
  }

  public GenericObject updateGenericObject(GenericObject genericObject, ObjectId id) {
    log.info("Updating a GenericObject (service layer): %s", id);
    Optional<GenericObject> genericObjectOptional = repository.findBy_Id(id);

    if (!genericObjectOptional.isPresent()) {
      throw new GenericObjectNotFoundException("id-" + id);
    }

    genericObject.set_Id(id);
    repository.save(genericObject);
    return genericObject;
  }

}
