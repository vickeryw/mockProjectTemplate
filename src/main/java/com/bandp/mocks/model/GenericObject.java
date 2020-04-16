package com.bandp.mocks.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
public class GenericObject {

  @Id
  @ApiModelProperty(readOnly = true, notes = "auto generated", position = 0)
  @Setter
  @Getter
  public ObjectId _Id;
  @ApiModelProperty(required = true, notes = "This can be any name")
  @Setter
  @Getter
  private String name;
  @ApiModelProperty(required = true, notes = "This can be any role")
  @Setter
  @Getter
  private String role;
  @ApiModelProperty(required = true, notes = "This can be any rank")
  @Setter
  @Getter
  private String rank;

  protected String getInfo() {
    return String.format("Name is %s role is %s", name, role);
  }
}
