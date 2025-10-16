package repository;

import domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    void save(User user);
    void delete(Long id);
    void load();
    void saveAll();
    List<User> findAll();
}
