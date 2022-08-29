package ist.challange.adeSalahuddin.controller;

import ist.challange.adeSalahuddin.entity.DTO.RegistrationDTO;
import ist.challange.adeSalahuddin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class userController {

    private final UserService userService;

    @GetMapping("/")
    public String Homepage() {
        return "Java Challange - Ade Salahuddin";
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registrationUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        return userService.registrationUsers(registrationDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody RegistrationDTO registrationDTO) throws Exception {
        return userService.loginUsers(registrationDTO);
    }

    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() throws Exception {
        return userService.findAllUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable long id) throws Exception {
        return userService.findById(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> editUsers(@PathVariable long id, @RequestBody RegistrationDTO registrationDTO) throws Exception {
        return userService.editUser(registrationDTO, id);
    }

}
