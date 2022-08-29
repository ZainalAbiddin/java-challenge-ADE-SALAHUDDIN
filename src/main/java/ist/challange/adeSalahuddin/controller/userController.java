package ist.challange.adeSalahuddin.controller;

import ist.challange.adeSalahuddin.entity.Users;
import ist.challange.adeSalahuddin.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class userController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    List<Users> getUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/login")
    public Users authenticateUser(@Valid @RequestBody Users users) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.findByUsername(users.getUsername()).orElseThrow(()->new Exception("User not found"));
    }
}
