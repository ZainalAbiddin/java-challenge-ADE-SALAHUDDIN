package ist.challange.adeSalahuddin.security;

import ist.challange.adeSalahuddin.entity.Users;
import ist.challange.adeSalahuddin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found " + userName));

        return AppUserDetails.build(user);
    }
}
