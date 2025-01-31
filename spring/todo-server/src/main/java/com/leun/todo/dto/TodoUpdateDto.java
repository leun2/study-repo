package com.leun.todo.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodoUpdateDto {
    private String todoTitle;
    private String todoDescription;
    private Boolean todoDone;
    private LocalDate todoDate;
}
