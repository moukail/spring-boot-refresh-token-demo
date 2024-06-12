package nl.moukafih.demo.service;

import nl.moukafih.demo.entity.User;
import nl.moukafih.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }

        User currentUser = user.get();

        org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(currentUser.getPassword())
                .roles(currentUser.getRole())
        ;

        return builder.build();
    }
}
