package com.bandp.mocks.repositories;

import com.bandp.mocks.models.GenericObject;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenericRepository extends MongoRepository<GenericObject, String> {

  Optional<GenericObject> findBy_Id(ObjectId _Id);

}
