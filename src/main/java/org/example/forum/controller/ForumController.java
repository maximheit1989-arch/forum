package org.example.forum.controller;

import lombok.RequiredArgsConstructor;
import org.example.forum.dto.CommentCreateDto;
import org.example.forum.dto.PostCreateDto;
import org.example.forum.dto.PostResponseDto;
import org.example.forum.service.ForumService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {
    private final ForumService service;

    @PostMapping("/post/{user}")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto addPost(
            @PathVariable
            String user,
            @RequestBody
            PostCreateDto dto
    ) {
        return service.addPost(user, dto);
    }

    @GetMapping("/post/{postId}")
    public PostResponseDto findById(@PathVariable Long postId) {
        return service.findPostById(postId);
    }

    @PatchMapping("/post/{postId}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addLike(@PathVariable Long postId) {
        service.addLike(postId);
    }

    @GetMapping("/posts/author/{user}")
    public List<PostResponseDto> findByAuthor(@PathVariable String user) {
        return service.findPostsByAuthor(user);
    }

    @PatchMapping("/post/{postId}/comment/{commenter}")
    public PostResponseDto addComment(@PathVariable Long postId, @PathVariable String commenter, @RequestBody CommentCreateDto dto) {
        return service.addComment(postId, commenter, dto);
    }

    @DeleteMapping("/post/{postId}")
    public PostResponseDto deletePost(@PathVariable Long postId) {
        return service.deletePost(postId);
    }

    @GetMapping("/posts/tags")
    public List<PostResponseDto> findByTags(@RequestParam List<String> values) {
        return service.findPostsByTags(values);
    }

    @GetMapping("/posts/period")
    public List<PostResponseDto> findByPeriod(@RequestParam LocalDate dateFrom, @RequestParam LocalDate dateTo) {
        return service.findPostsByPeriod(dateFrom, dateTo);
    }

    @PatchMapping("/post/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @RequestBody PostCreateDto dto) {
        return service.updatePost(postId, dto);
    }


}
