package tch_tech.com.userservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;
import tch_tech.com.userservice.repository.UserRepository;
import tch_tech.com.userservice.repository.UserRoleRespository;

import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class UserService implements UserServiceInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRespository userRoleRepository;

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
        UserEntity user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        UserRole role = userRoleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }


    @Override
    public UserEntity loadUserByUsername(String username) {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    public List<UserEntity> getAllUsers() {
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

    // Save user
    public UserEntity saveUser(UserEntity userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            throw new RuntimeException(
                    "User with email " + userEntity.getEmail() + " already exists"
            );
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    // delete user
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }


}
