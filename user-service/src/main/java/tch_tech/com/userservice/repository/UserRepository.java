package tch_tech.com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tch_tech.com.userservice.model.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByFirstName(String firstName);
    Optional<UserEntity> findByLastName(String lastName);
    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmail(String email);


}
