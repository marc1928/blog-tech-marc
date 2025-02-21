package tch_tech.com.userservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tch_tech.com.userservice.model.UserEntity;
import tch_tech.com.userservice.model.UserRole;
import tch_tech.com.userservice.repository.UserRepository;
import tch_tech.com.userservice.service.UserService;

import java.util.ArrayList;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            userService.addNewRole(new UserRole(null , "USER"));
            userService.addNewRole(new UserRole(null , "ADMIN"));

            userService.addNewUser(new UserEntity(null, "FirstName1", "LastName1", "12D2D1", "user1@example.com", new ArrayList<>(), "firstLastName1"));
            userService.addNewUser(new UserEntity(null, "Firstadm", "Lastadm", "12D2D1", "admin@example.com", new ArrayList<>(), "AdminUser"));

            userService.addRoleToUser("firstLastName1", "USER");
            userService.addRoleToUser("AdminUser", "ADMIN");


        };
    }

}
