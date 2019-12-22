package pl.studentsevent.studentseventapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.studentsevent.studentseventapp.model.user.CustomUserDetails;
import pl.studentsevent.studentseventapp.model.user.User;
import pl.studentsevent.studentseventapp.respository.UserRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByName(username) ;

        optionalUser
                .orElseThrow(()->new UsernameNotFoundException("username not found"));

        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}
