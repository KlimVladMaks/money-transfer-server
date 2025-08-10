package me.klimvlad.moneytransfer.server.repository;

import me.klimvlad.moneytransfer.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
    Optional<User> findBySessionId(String sessionId);
}
