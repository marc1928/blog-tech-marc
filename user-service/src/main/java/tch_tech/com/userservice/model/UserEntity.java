package tch_tech.com.userservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<UserRole> role;
}
