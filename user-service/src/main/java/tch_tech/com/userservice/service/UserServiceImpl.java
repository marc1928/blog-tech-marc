package tch_tech.com.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;
import tch_tech.com.userservice.repository.UserRepository;
import tch_tech.com.userservice.repository.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity addNewUser(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RuntimeException("User with email " + userEntity.getEmail() + " already exists");
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public UserRole addNewRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username " + username + " not found"));
        UserRole userRole = userRoleRepository.findByRoleName(roleName);
        userEntity.getUserRoles().add(userRole);
        userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
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

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
