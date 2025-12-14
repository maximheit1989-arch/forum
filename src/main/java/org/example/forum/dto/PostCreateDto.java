package org.example.forum.dto;

import lombok.Data;

import java.util.List;

@Data
public class PostCreateDto {
    private String title;
    private String content;
    private List<String> tags;
}
