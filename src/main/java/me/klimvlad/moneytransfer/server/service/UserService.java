package me.klimvlad.moneytransfer.server.service;

import me.klimvlad.moneytransfer.server.model.User;
import me.klimvlad.moneytransfer.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User registerUser(String login, String password, String description) {
        if (userRepository.existsByLogin(login)) {
            throw new RuntimeException("Login already exists");
        }
        
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setDescription(description);
        
        return userRepository.save(user);
    }
}
