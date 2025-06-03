package backend.medium.blog_one.service;

import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.User;
import backend.medium.blog_one.repository.BlogRepository;
import backend.medium.blog_one.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;


    @Autowired
    public BlogService(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    public void saveBlog(Blog blog, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        blog.setUser(user);
        blog.setCreatedAt(LocalDateTime.now());
        blogRepository.save(blog);
    }

    public Blog findById(Long id){
        return blogRepository.findById(id).orElseThrow();
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public void deleteBlog(Long id){
        blogRepository.delete(findById(id));
    }


}
