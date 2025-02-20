package tch_tech.com.userservice.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> updateUser(Long id, UserEntity userEntity) {
        return userRepository.findById(id).map(
                existingUser -> {
                    existingUser.setFirstName(userEntity.getFirstName());
                    existingUser.setLastName(userEntity.getLastName());
                    existingUser.setEmail(userEntity.getEmail());
                    existingUser.setPassword(passwordEncoder.encode(userEntity.getPassword()));
                    return userRepository.save(existingUser);
                }
        );
    }

    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }

    public UserEntity saveUser(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RuntimeException(
                    "User with email " + userEntity.getEmail() + " already exists"
            );
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

}
