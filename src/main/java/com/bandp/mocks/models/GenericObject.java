package com.bandp.mocks.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

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

  public GenericObject(ObjectId _Id, String name, String role, String rank) {
    this._Id = _Id;
    this.name = name;
    this.role = role;
    this.rank = rank;
  }
}
