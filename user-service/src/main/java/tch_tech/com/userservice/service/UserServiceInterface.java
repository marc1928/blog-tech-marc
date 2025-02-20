package tch_tech.com.userservice.service;

import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    UserEntity addNewUser(UserEntity userEntity);
    UserRole addNewRole(UserRole userRole);
    void addRoleToUser(String username, String roleName);
    UserEntity loadUserByUsername(String username);
    List<UserEntity> listAllUsers();
    Optional<UserEntity> getUserById(Long id);
    Optional<UserEntity> updateUser(Long id, UserEntity userEntity);
    void deleteUserById(Long id);
}
