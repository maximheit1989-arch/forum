package org.example.forum.serviceImpl;

import org.example.forum.dto.CommentCreateDto;
import org.example.forum.dto.PostCreateDto;
import org.example.forum.dto.PostResponseDto;
import org.example.forum.exception.PostNotFoundException;
import org.example.forum.service.ForumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {

    @Override
    public PostResponseDto addPost(String user, PostCreateDto dto) {
        return new PostResponseDto();
    }

    @Override
    public PostResponseDto findPostById(Long postID) {
        throw new PostNotFoundException();
    }

    @Override
    public void addLike(Long postId) {
        throw new PostNotFoundException();
    }

    @Override
    public List<PostResponseDto> findPostsByAuthor(String author) {
        return List.of();
    }

    @Override
    public PostResponseDto addComment(Long postId, String commenter, CommentCreateDto dto) {
        throw new PostNotFoundException();
    }

    @Override
    public PostResponseDto deletePost(Long postId) {
        throw new PostNotFoundException();
    }

    @Override
    public List<PostResponseDto> findPostsByTags(List<String> tags) {
        return List.of();
    }

    @Override
    public List<PostResponseDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return List.of();
    }

    @Override
    public PostResponseDto updatePost(Long postId, PostCreateDto dto) {
        throw new PostNotFoundException();
    }
}
