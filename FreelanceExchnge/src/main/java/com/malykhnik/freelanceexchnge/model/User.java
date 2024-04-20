package com.malykhnik.freelanceexchnge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    @Column(name = "tel_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
