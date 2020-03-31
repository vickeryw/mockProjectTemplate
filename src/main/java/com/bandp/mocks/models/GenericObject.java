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
    @Setter
    @Getter
    private String name;
    @Setter
    @Getter
    private String role;
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
