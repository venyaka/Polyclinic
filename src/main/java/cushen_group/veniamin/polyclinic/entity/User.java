package cushen_group.veniamin.polyclinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Extract> extracts = new ArrayList<>();

    @Column(name = "date_create")
    private LocalDateTime dateCreate;

    @Column(name = "is_email_verificated")
    private Boolean isEmailVerificated;

    @Column(name = "token")
    private String token;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Set<Role> roles = new HashSet<>();

    @PrePersist
    public void prePersist() {
        this.dateCreate = LocalDateTime.now();
        if (roles.isEmpty()) {
            roles.add(Role.ROLE_PATIENT);
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }

        User otherUser = (User) obj;
        return this.getId().equals(otherUser.getId());
    }
}

