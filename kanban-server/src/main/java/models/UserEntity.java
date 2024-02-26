package models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;
    @Column(nullable = false, unique = true)
    String password;

    @Column(nullable = false)
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "permission",
            joinColumns = @JoinColumn(name = "Users_id"),
            inverseJoinColumns = @JoinColumn(name = "Permission_id")
    )
    private HashSet<PermissionEntity> permissions = new HashSet<>();

}
