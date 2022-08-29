package ist.challange.adeSalahuddin.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, unique = true,nullable = false)
    @Size(min = 3, max = 25)
    private String username;

    @Column(length = 25,nullable = false)
    @Size(min = 3, max = 25)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<Role> roles = new ArrayList<>();
}
