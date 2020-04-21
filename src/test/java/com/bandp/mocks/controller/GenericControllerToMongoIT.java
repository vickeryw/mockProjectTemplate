package com.bandp.mocks.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bandp.mocks.model.GenericObject;
import com.bandp.mocks.repository.GenericRepository;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@TestPropertySource(locations = {"classpath:/integrationtest.properties"})
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootTest
public class GenericControllerToMongoIT {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GenericRepository genericRepository;

  @DisplayName("Post a single GenericObject successfully")
  @Test
  public void shouldPostSuccessfully() {
    final String name = "Jill";
    final String role = "Carer";
    final String rank = "Staff";
    GenericObject genericObject = new GenericObject(new ObjectId(), name, role, rank);
    testPost(genericObject, name, role, rank);
  }

  @DisplayName("Get all GenericObject's successfully")
  @Test
  public void shouldGetAllSuccessfully() {
    String name = "Mr Getter";
    String role = "getter role";
    String rank = "General";
    GenericObject genericObject = new GenericObject(new ObjectId(), name, role, rank);
    List<GenericObject> list = new ArrayList<>();
    list.add(genericObject);
    Mockito.when(genericRepository.findAll()).thenReturn(list);
    testGetAll(name, role, rank);
  }

  @DisplayName("Get a single GenericObject successfully")
  @Test
  public void shouldGetSuccessfully() {
    ObjectId objectId = new ObjectId();
    String name = "Mr Getter";
    String role = "getter role";
    String rank = "General";
    GenericObject genericObject = new GenericObject(objectId, name, role, rank);
    List<GenericObject> list = new ArrayList<>();
    list.add(genericObject);
    Mockito.when(genericRepository.findBy_Id(objectId)).thenReturn(Optional.of(genericObject));
    testGet(objectId, name, role, rank);
  }

  @DisplayName("Update a single GenericObject Successfully")
  @Test
  public void shouldUpdateSuccessfully() {
    ObjectId objectId = new ObjectId();
    final String name = "Jill";
    final String role = "Carer";
    final String rank = "Staff";
    final String newName = "Bill";
    final String newRole = "Junior Developer";
    final String newRank = "Graduate";
    GenericObject genericObject = new GenericObject(objectId, name, role, rank);
    Mockito.when(genericRepository.findBy_Id(objectId)).thenReturn(Optional.of(genericObject));
    GenericObject newGenericObject = new GenericObject(objectId, newName, newRole, newRank);
    testUpdate(objectId, newGenericObject, newName, newRole, newRank);
  }

  @DisplayName("Delete a single GenericObject Successfully")
  @Test
  public void shouldDeleteSuccessfully() {
    ObjectId objectId = new ObjectId();
    String name = "Mr Getter";
    String role = "getter role";
    String rank = "General";
    GenericObject genericObject = new GenericObject(objectId, name, role, rank);
    Mockito.when(genericRepository.findBy_Id(objectId)).thenReturn(Optional.of(genericObject));
    testDelete(objectId);
  }

  @SneakyThrows
  private void testPost(GenericObject genericObject, String expectedName, String expectedRole, String expectedRank) {
    Gson gson = new Gson();
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/genericToMongo")
        .content(gson.toJson(genericObject))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();

    String resultString = result.getResponse().getContentAsString();
    GenericObject responseObject = gson.fromJson(resultString, GenericObject.class);
    assertThat(resultString).isNotNull();
    assertThat(responseObject.getName()).isEqualTo(expectedName);
    assertThat(responseObject.getRole()).isEqualTo(expectedRole);
    assertThat(responseObject.getRank()).isEqualTo(expectedRank);
  }

  @SneakyThrows
  private void testGetAll(String expectedName, String expectedRole, String expectedRank) {
    Gson gson = new Gson();
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genericToMongo")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();

    String resultString = result.getResponse().getContentAsString();
    Type listType = new TypeToken<ArrayList<GenericObject>>(){}.getType();
    List<GenericObject> responseObject = gson.fromJson(resultString, listType);
    assertThat(responseObject.get(0).getName()).isEqualTo(expectedName);
    assertThat(responseObject.get(0).getRole()).isEqualTo(expectedRole);
    assertThat(responseObject.get(0).getRank()).isEqualTo(expectedRank);
  }

  @SneakyThrows
  private void testGet(ObjectId id, String expectedName, String expectedRole, String expectedRank) {
    Gson gson = new Gson();
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/genericToMongo/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andReturn();

    String resultString = result.getResponse().getContentAsString();
    GenericObject responseObject = gson.fromJson(resultString, GenericObject.class);
    assertThat(resultString).isNotNull();
    assertThat(responseObject.getName()).isEqualTo(expectedName);
    assertThat(responseObject.getRole()).isEqualTo(expectedRole);
    assertThat(responseObject.getRank()).isEqualTo(expectedRank);
  }

  @SneakyThrows
  private void testUpdate(ObjectId id, GenericObject genericObject, String expectedName, String expectedRole, String expectedRank) {
    Gson gson = new Gson();
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/genericToMongo/{id}", id)
        .content(gson.toJson(genericObject))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is(202))
        .andReturn();

    String resultString = result.getResponse().getContentAsString();
    GenericObject responseObject = gson.fromJson(resultString, GenericObject.class);
    assertThat(resultString).isNotNull();
    assertThat(responseObject.getName()).isEqualTo(expectedName);
    assertThat(responseObject.getRole()).isEqualTo(expectedRole);
    assertThat(responseObject.getRank()).isEqualTo(expectedRank);
  }

  @SneakyThrows
  private void testDelete(ObjectId id) {
    mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/genericToMongo/{id}", id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(204));
  }

}
