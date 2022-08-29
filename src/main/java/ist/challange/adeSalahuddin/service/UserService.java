package ist.challange.adeSalahuddin.service;

import ist.challange.adeSalahuddin.entity.DTO.RegistrationDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> findAllUsers()throws Exception;
    ResponseEntity<Object> findById(long id) throws Exception;
    ResponseEntity<Object> loginUsers(RegistrationDTO registrationDTO) throws Exception;
    ResponseEntity<Object> registrationUsers(RegistrationDTO registrationDTO) throws Exception;
    ResponseEntity<Object> editUser(RegistrationDTO registrationDTO,long id)throws Exception;

}
