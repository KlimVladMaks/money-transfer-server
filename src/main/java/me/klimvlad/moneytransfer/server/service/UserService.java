package me.klimvlad.moneytransfer.server.service;

import me.klimvlad.moneytransfer.server.model.User;
import me.klimvlad.moneytransfer.server.repository.UserRepository;

import java.util.UUID;

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

    public String loginUser(String login, String password) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        
        // Генерируем "сессию" (просто случайный UUID)
        String sessionId = UUID.randomUUID().toString();
        user.setSessionId(sessionId);
        userRepository.save(user);
        
        return sessionId;
    }

    public void logoutUser(String sessionId) {
        User user = userRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        user.setSessionId(null); // Удаляем сессию
        userRepository.save(user);
    }
}
