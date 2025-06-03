package backend.medium.blog_one.controllers;

import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.Comment;
import backend.medium.blog_one.entity.User;
import backend.medium.blog_one.service.BlogService;
import backend.medium.blog_one.service.CommentService;
import backend.medium.blog_one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

    private final BlogService blogService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public BlogController(BlogService blogService, UserService userService, CommentService commentService) {
        this.blogService = blogService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @GetMapping("/create-blog")
    public String createBlog(){
        return "create-blog";
    }

    @PostMapping("/post-blog")
    public ResponseEntity<?> postBlog(@RequestBody Blog blog, Principal principal){
        blogService.saveBlog(blog, principal);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/blogs")
    public String viewAllBlogs(Model model){
        List<Blog> blogs = blogService.getAllBlogs();
        model.addAttribute("blogs", blogs);
        return "public-blogs";
    }

    @GetMapping("/blogs/{id}")
    public String viewBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.findById(id);
        List<Comment> comments = commentService.getAllCommentsOfBlog(blog);

        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);

        return "view-blog";
    }

    @PostMapping("/blogs/{id}/comment")
    public ResponseEntity<?> postComment(@PathVariable Long id, @RequestBody Comment comment, Principal principal){
        Blog blog = blogService.findById(id);
        User user = userService.findUserByUsername(principal.getName());
        comment.setBlog(blog);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());

        commentService.saveComment(comment);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/edit-blog/{id}")
    public String editBlog(
            @PathVariable Long id,
            Principal principal,
            Model model
    ){
        Blog blog = blogService.findById(id);
        // only the blog author can edit
        if(!blog.getUser().getUsername().equals(principal.getName())){
            return "redirect:/home";
        }

        model.addAttribute("blog", blog);
        return "edit-blog";
    }

    @PatchMapping("/edit-blog/{id}")
    public ResponseEntity<?> patchBlog(@PathVariable Long id, @RequestBody Blog blog, Principal principal){
        Blog updatedBlog = blogService.findById(id);
        updatedBlog.setTitle(blog.getTitle());
        updatedBlog.setContent(blog.getContent());
        blogService.saveBlog(updatedBlog, principal);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-blog/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id){
        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();

    }
}


