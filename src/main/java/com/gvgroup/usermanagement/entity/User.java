package com.gvgroup.usermanagement.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;

    @Column(unique = true, nullable = false)
    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String idNumber;

    @Column(unique = true)
    private String email;

    private Boolean isActive;

    private Instant createDate;

    private Instant updateDate;

    @ManyToMany
    private List<Role> roles;
}
