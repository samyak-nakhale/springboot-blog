package backend.medium.blog_one.service;

import backend.medium.blog_one.entity.User;
import backend.medium.blog_one.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user){
        boolean isUserPresent = userRepository.findByUsername(user.getUsername()).isPresent();
        if(isUserPresent){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public User findUserByUsername(String name){
        return userRepository.findByUsername(name).orElseThrow();
    }
}
