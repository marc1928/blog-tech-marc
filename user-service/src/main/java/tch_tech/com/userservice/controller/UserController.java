package tch_tech.com.userservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;
import tch_tech.com.userservice.service.UserServiceImpl;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
public class UserController {

//    private final UserServiceImpl userService;
    private final UserServiceImpl userServiceImpl;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/api/users")
    public List<UserEntity> getAllUsers() {
        return userServiceImpl.listAllUsers();
    }

    @GetMapping("/api/users/{id}")
    public Optional<UserEntity> getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id);
    }
    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
        logger.info("Received request: {}", userEntity);
        UserEntity savedUser = userServiceImpl.addNewUser(userEntity);
        return ResponseEntity.ok(savedUser);
    }


    @PutMapping("/api/users/{id}")
    public Optional<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return userServiceImpl.updateUser(id, userEntity);
    }

    @DeleteMapping("/api/users/{id}")
    public String deleteUserById(@PathVariable Long id) {
        userServiceImpl.deleteUserById(id);
        return "User deleted successfully";
    }
    @PostMapping("/api/users/roles")
    public UserRole saveRole(@RequestBody UserRole userRole) {
        return userServiceImpl.addNewRole(userRole);
    }


    @PostMapping("/api/users/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        userServiceImpl.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }


}
@Data
class RoleUserForm {
    private String roleName;
    private String username;
}
