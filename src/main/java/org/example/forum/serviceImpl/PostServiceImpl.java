package org.example.forum.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.forum.dao.CommentRepository;
import org.example.forum.dao.PostRepository;
import org.example.forum.dao.TagRepository;
import org.example.forum.dto.CommentCreateDto;
import org.example.forum.dto.CommentDto;
import org.example.forum.dto.PostCreateDto;
import org.example.forum.dto.PostResponseDto;
import org.example.forum.exception.PostNotFoundException;
import org.example.forum.model.Comment;
import org.example.forum.model.Post;
import org.example.forum.model.Tag;
import org.example.forum.service.ForumService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements ForumService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;

    private PostResponseDto toDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setDateCreated(post.getDateCreated());
        dto.setLikes(post.getLikes());

        dto.setTags(
                post.getTags()
                        .stream()
                        .map(Tag::getName)
                        .toList()
        );

        dto.setComments(
                post.getComments()
                        .stream()
                        .map(c -> {
                            CommentDto cd = new CommentDto();
                            cd.setUser(c.getUsername());
                            cd.setMessage(c.getMessage());
                            cd.setDateCreated(c.getDateCreated());
                            cd.setLikes(c.getLikes());
                            return cd;
                        })
                        .toList()
        );
        return dto;
    }

    @Override
    public PostResponseDto addPost(String user, PostCreateDto dto) {
        Post post = new Post(
                dto.getTitle(),
                dto.getContent(),
                user,
                new HashSet<>(dto.getTags())
        );
        post.getTags().forEach(tagRepository::save);
        Post saved = postRepository.save(post);
        return toDto(saved);
    }

    @Override
    public PostResponseDto findPostById(Long postID) {
        Post post = postRepository.findById(postID)
                .orElseThrow(PostNotFoundException::new);
        return toDto(post);
    }

    @Override
    public void addLike(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);
        post.addLike();
        postRepository.save(post);
    }

    @Override
    public List<PostResponseDto> findPostsByAuthor(String author) {
        return postRepository.findAll()
                .stream()
                .filter(p -> p.getAuthor().equals(author))
                .map(this::toDto)
                .toList();
    }

    @Override
    public PostResponseDto addComment(Long postId, String commenter, CommentCreateDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = new Comment(commenter, dto.getMessage());
        comment.setPost(post);

        post.addComment(comment);

        commentRepository.save(comment);
        postRepository.save(post);

        return toDto(post);
    }

    @Override
    public PostResponseDto deletePost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.delete(post);
        return toDto(post);
    }

    @Override
    public List<PostResponseDto> findPostsByTags(List<String> tags) {
        return postRepository.findAll()
                .stream()
                .filter(p ->
                        p.getTags()
                                .stream()
                                .anyMatch(t -> tags.contains(t.getName()))
                )
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<PostResponseDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return postRepository.findAll()
                .stream()
                .filter(p -> {
                    LocalDate date = p.getDateCreated().toLocalDate();
                    return !date.isBefore(dateFrom) && !date.isAfter(dateTo);
                })
                .map(this::toDto)
                .toList();
    }

    @Override
    public PostResponseDto updatePost(Long postId, PostCreateDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        post.getTags().clear();
        post.getTags().addAll(
                dto.getTags().stream().map(Tag::new).toList()
        );

        post.getTags().forEach(tagRepository::save);

        return toDto(postRepository.save(post));
    }
}