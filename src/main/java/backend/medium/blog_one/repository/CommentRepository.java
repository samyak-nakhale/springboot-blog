package backend.medium.blog_one.repository;


import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.Comment;
import backend.medium.blog_one.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.blog =:blog ORDER BY c.createdAt DESC")
    List<Comment> findByBlogOrderByCreatedAtDesc(@Param("blog") Blog blog);
}
