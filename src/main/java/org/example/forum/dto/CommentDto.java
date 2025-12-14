package org.example.forum.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private int likes;

}
