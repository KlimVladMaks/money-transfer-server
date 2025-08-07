package me.klimvlad.moneytransfer.server.controller;

import me.klimvlad.moneytransfer.server.model.User;
import me.klimvlad.moneytransfer.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
