package me.klimvlad.moneytransfer.server.controller;

import me.klimvlad.moneytransfer.server.dto.AuthResponse;
import me.klimvlad.moneytransfer.server.model.User;
import me.klimvlad.moneytransfer.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam(required = false) String description) {
        User user = userService.registerUser(login, password, description);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(
            @RequestParam String login,
            @RequestParam String password
    ) {
        try {
            String sessionId = userService.loginUser(login, password);
            return ResponseEntity.ok(new AuthResponse(sessionId)); // JSON автоматически!
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Пустое тело или можно добавить DTO с ошибкой
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(@RequestParam String sessionId) {
        try {
            userService.logoutUser(sessionId);
            return ResponseEntity.ok("Logged out successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
