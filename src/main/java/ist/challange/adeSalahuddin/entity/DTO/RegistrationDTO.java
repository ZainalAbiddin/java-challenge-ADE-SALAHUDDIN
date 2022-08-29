package ist.challange.adeSalahuddin.entity.DTO;

import ist.challange.adeSalahuddin.entity.Users;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String username;
    private String password;
    public Users saveToEntity(){
        return Users.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
