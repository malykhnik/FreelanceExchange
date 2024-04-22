package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Transient
    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role(String roleName) {
        this.roleName = roleName;
    }


    @Override
    public String getAuthority() {
        return getRoleName();
    }

}
