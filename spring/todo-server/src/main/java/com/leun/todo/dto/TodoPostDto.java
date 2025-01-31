package com.leun.todo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoPostDto {

    private String todoTitle;
    private String todoDescription;
}
