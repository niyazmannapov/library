package ru.itis.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private State state;
    @Column(nullable = false)
    private String confirmCode;

    @Transient
    public boolean isAdmin() {
        return Role.ADMIN.equals(role);
    }



}
