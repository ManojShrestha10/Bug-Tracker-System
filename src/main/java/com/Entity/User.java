package com.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = true, length = 50)
    private String userName;

    @Column(name = "user_email", nullable = false, unique = true, length = 255)
    private String email;

    // Make sure to hash passwords before storing them
    @Column(name = "user_password", nullable = false, length = 255)
    private String password;

    @Column(name = "user_enabled", nullable = false)
    private boolean enabled = true;

    // Many users can have many roles(e.g.. ADMIN, DEVELOPER, TESTER)
    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<>();

    // Bug created by the user date
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    // Bug last updated by the user date
    @Column(name = "updated_at")
    private LocalDate updatedDate;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = LocalDate.now();
    }

}
