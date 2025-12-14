package org.example.forum.service;

import org.example.forum.dto.CommentCreateDto;
import org.example.forum.dto.PostCreateDto;
import org.example.forum.dto.PostResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ForumService {
    PostResponseDto addPost(String user, PostCreateDto dto);

    PostResponseDto findPostById(Long postID);

    void addLike(Long postId);

    List<PostResponseDto> findPostsByAuthor(String author);

    PostResponseDto addComment(Long postId, String commenter, CommentCreateDto dto);

    PostResponseDto deletePost(Long postId);

    List<PostResponseDto> findPostsByTags(List<String> tags);

    List<PostResponseDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);

    PostResponseDto updatePost(Long postId, PostCreateDto dto);

}
