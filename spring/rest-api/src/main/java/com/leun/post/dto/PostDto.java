package com.leun.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    @NotBlank(message = "Post title is required")
    @Size(min = 2, max = 40, message = "Title must be between 2 and 40 characters")
    private String postTitle;

    private String postDescription;
}
