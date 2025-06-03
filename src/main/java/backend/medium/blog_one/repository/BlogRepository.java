package backend.medium.blog_one.repository;

import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b WHERE b.user =:user")
    List<Blog> findByUser(@Param("user") User user);

    @Query("SELECT b FROM Blog b ORDER BY b.createdAt DESC")
    List<Blog> findAllByOrderByCreatedAtDesc();
}
