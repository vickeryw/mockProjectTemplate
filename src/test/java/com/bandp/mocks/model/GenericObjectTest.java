package com.bandp.mocks.model;

import static com.google.common.truth.Truth.assertThat;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

public class GenericObjectTest {

  @Test
  public void shouldGetCorrectInfoEmptyConstructor() {
    GenericObject genericObject = new GenericObject();
    genericObject.setName("Bill");
    genericObject.setRole("Front line");
    assertThat(genericObject.getInfo()).isEqualTo("Name is Bill role is Front line");
    genericObject.getInfo();
  }

  @Test
  public void shouldGetCorrectInfoInitConstructor() {
    ObjectId objectId = new ObjectId();
    GenericObject genericObject = new GenericObject(objectId, "Bill", "Front line", "General");
    assertThat(genericObject.getInfo()).isEqualTo("Name is Bill role is Front line");
    assertThat(genericObject.getRank()).isEqualTo("General");
    genericObject.getInfo();
  }

}
