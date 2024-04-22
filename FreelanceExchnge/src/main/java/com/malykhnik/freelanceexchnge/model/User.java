package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    private String name;

    @Column(name = "tel_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

}
