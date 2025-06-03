package backend.medium.blog_one.controllers;

import backend.medium.blog_one.entity.Blog;
import backend.medium.blog_one.entity.User;
import backend.medium.blog_one.repository.BlogRepository;
import backend.medium.blog_one.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final UserRepository userRepository;
    private final BlogRepository blogRepository;

    @Autowired
    public HomeController(UserRepository userRepository, BlogRepository blogRepository) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<Blog> blogs = blogRepository.findByUser(user);
        model.addAttribute("blogs", blogs);
        return "home";
    }
}
