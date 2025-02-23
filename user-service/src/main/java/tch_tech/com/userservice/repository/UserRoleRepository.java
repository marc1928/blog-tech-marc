
package tch_tech.com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tch_tech.com.userservice.model.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRoleName(String roleName);
}
