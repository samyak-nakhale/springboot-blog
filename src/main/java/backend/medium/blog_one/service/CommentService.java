package backend.medium.blog_one.service;

import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.Comment;
import backend.medium.blog_one.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsOfBlog(Blog blog){
        return commentRepository.findByBlogOrderByCreatedAtDesc(blog);
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
}
