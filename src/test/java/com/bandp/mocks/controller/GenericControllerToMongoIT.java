package com.bandp.mocks.controller;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bandp.mocks.model.GenericObject;
import com.bandp.mocks.repository.GenericRepository;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
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
//@ContextConfiguration(classes = {GenericControllerToMongo.class, GenericObject.class})
//@WebMvcTest
//@DataMongoTest
@TestPropertySource(locations = "classpath:integrationtest.properties")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenericControllerToMongoIT {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  GenericControllerToMongo genericControllerToMongo;

  @MockBean
  GenericRepository genericRepository;

//  @MockBean
//  GenericObject genericObject;

  @SneakyThrows
  @Test
  public void shouldPostSuccessfully() {
    final String name = "Jill";
    final String role = "Carer";
    final String rank = "Staff";
    GenericObject genericObject = new GenericObject(new ObjectId(), name, role, rank);
    testPost(genericObject, name, role, rank);
  }

  private void testPost(GenericObject genericObject, String expectedName, String expectedRole, String expectedRank) throws Exception {
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

}
