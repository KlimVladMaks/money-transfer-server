package me.klimvlad.moneytransfer.server.repository;

import me.klimvlad.moneytransfer.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByLogin(String login);
}
