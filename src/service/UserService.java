package service;

import domain.User;
import repository.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //회원가입
    public Optional<User> signUp(String name, String password) {
        if (password == null || password.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("모든 항목을 입력해야 합니다.");
        }

        if(userRepository.findByUsername(name).isPresent()) {
            return Optional.empty();
        }
        User user = new User(name, password);
        userRepository.save(user);
        return Optional.of(user);

        /*userRepository.findByUsername(name).ifPresent(user -> {
            throw new IllegalStateException("이미 존재하는 사용자명입니다.");
        });
        User user = new User(name, password);
        return userRepository.save(user);*/
    }

    //로그인
    public Optional<User> login(String name, String password) {
        return userRepository.findByUsername(name).filter(u -> u.getUserPassword().equals(password));
    }

    //회원 탈퇴
    public boolean deleteUser(Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user.getId());
                    return true;
                })
                .orElse(false);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
