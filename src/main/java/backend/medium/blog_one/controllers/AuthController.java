package backend.medium.blog_one.controllers;

import backend.medium.blog_one.entity.User;
import backend.medium.blog_one.repository.UserRepository;
import backend.medium.blog_one.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @PostMapping("/do-register")
    public String doRegister(@ModelAttribute User user){
        boolean result = userService.registerUser(user);
        if(!result){
            return "redirect:/register?exist";
        }
        else{
            return "redirect:/home";
        }
    }
}
