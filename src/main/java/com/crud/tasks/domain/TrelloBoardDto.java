package com.crud.tasks.domain;

//import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
//import lombok.Getter;
//import lombok.NoArgsConstructor;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class TrelloBoardDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("id")
    private String id;

    @JsonProperty("lists")
    private List<TrelloListDto> lists;
}
