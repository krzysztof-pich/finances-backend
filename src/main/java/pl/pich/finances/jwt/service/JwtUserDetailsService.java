package pl.pich.finances.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.pich.finances.user.service.UserService;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<pl.pich.finances.user.model.User> user = userService.getUserByEmail(username);
        if (user.isPresent()) {
            return new User(user.get().getEmail(), user.get().getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public pl.pich.finances.user.model.User loadUserByEmail(String username) throws UsernameNotFoundException {
        Optional<pl.pich.finances.user.model.User> user = userService.getUserByEmail(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}