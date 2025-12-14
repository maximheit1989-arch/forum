package org.example.forum.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PostResponseDto {
    private Long id;
    private  String title;
    private String content;
    private String author;
    private LocalDateTime dateCreated;
    private List<String> tags;
    private int likes;
    private List<CommentDto> comments;
}
