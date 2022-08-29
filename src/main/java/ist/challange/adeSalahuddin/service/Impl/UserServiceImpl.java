package ist.challange.adeSalahuddin.service.Impl;

import ist.challange.adeSalahuddin.entity.DTO.RegistrationDTO;
import ist.challange.adeSalahuddin.entity.Role;
import ist.challange.adeSalahuddin.entity.Users;
import ist.challange.adeSalahuddin.repository.RoleRepository;
import ist.challange.adeSalahuddin.repository.UserRepository;
import ist.challange.adeSalahuddin.responseHandler.ResponseHandler;
import ist.challange.adeSalahuddin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final String message409 = "Username sudah terpakai";
    private final String message400 = "Username dan / atau password kosong";

    @Override
    public ResponseEntity<Object> findAllUsers() throws Exception {
        try {
            List<Users> users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new Exception("Users not found");
            }
            return ResponseHandler.generateResponse("Success Retrieved User", HttpStatus.OK, users);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Users Not Found");
        }
    }

    @Override
    public ResponseEntity<Object> findById(long id) throws Exception {
        try {
            Users user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
            return ResponseHandler.generateResponse("Success Retrieved User", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, "Users Not Found");
        }

    }

    @Override
    public ResponseEntity<Object> loginUsers(RegistrationDTO registrationDTO) throws Exception {
        try {
            if (registrationDTO.getUsername() == null || registrationDTO.getPassword() == null) {
                throw new Exception(message400);
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationDTO.getUsername(), registrationDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Users user = userRepository.findByUsername(registrationDTO.getUsername()).orElseThrow(() -> new Exception("User tidak ditemukan"));
            return ResponseHandler.generateResponse("Sukses Login", HttpStatus.OK, user);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, "Login Failed");
        }
    }

    @Override
    public ResponseEntity<Object> registrationUsers(RegistrationDTO registrationDTO) throws Exception {
        try {
            if (registrationDTO.getUsername() == null || registrationDTO.getPassword() == null) {
                throw new Exception(message400);
            }
            Optional<Role> roleCheck = roleRepository.findByName("ROLE_USER");
            Role role;
            if (roleCheck.isEmpty()) {
                Role newRole = new Role();
                newRole.setName("ROLE_USER");
                role = roleRepository.save(newRole);
            } else {
                role = roleCheck.get();
            }
            Optional<Users> users = userRepository.findByUsername(registrationDTO.getUsername());
            if (users.isPresent()) {
                throw new Exception(message409);
            }
            Users user = registrationDTO.saveToEntity();
            user.setRoles(Collections.singleton(role));
            Users userResponse = userRepository.save(user);
            return ResponseHandler.generateResponse("Sukses Registrasi", HttpStatus.CREATED, userResponse);
        } catch (Exception e) {
            HttpStatus status;
            if (e.getMessage().equals(message409)) {
                status = HttpStatus.CONFLICT;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
            return ResponseHandler.generateResponse(e.getMessage(), status, "Login Failed");
        }
    }

    @Override
    public ResponseEntity<Object> editUser(RegistrationDTO registrationDTO, long id) throws Exception {
        try {
            if (registrationDTO.getUsername() == null || registrationDTO.getPassword() == null) {
                throw new Exception(message400);
            }
            Users users = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));
            Optional<Users> checkUsername = userRepository.findByUsername(registrationDTO.getUsername());
            if (checkUsername.isPresent()) {
                throw new Exception(message409);
            }
            if (registrationDTO.getPassword().equals(users.getPassword())) {
                throw new Exception("Password tidak boleh sama dengan password sebelumnya");
            }
            users.setUsername(registrationDTO.getUsername());
            users.setPassword(registrationDTO.getPassword());
            Users user = userRepository.save(users);
            return ResponseHandler.generateResponse("Sukses Edit", HttpStatus.CREATED, user);
        } catch (Exception e) {
            HttpStatus status;
            if (e.getMessage().equals(message409)) {
                status = HttpStatus.CONFLICT;
            } else {
                status = HttpStatus.BAD_REQUEST;
            }
            return ResponseHandler.generateResponse(e.getMessage(), status, "Login Failed");
        }

    }

}
