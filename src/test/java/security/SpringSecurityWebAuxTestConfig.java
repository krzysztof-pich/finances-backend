package security;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.pich.finances.jwt.model.ExtendedUserDetails;
import pl.pich.finances.user.model.User;

import java.util.ArrayList;
import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User user = new User();
        user.setEmail("test@pich.pl");
        user.setPassword("laser");
        ExtendedUserDetails basicActiveUser = new ExtendedUserDetails(user.getEmail(), user.getPassword(), user, new ArrayList<>());



        return new InMemoryUserDetailsManager(Arrays.asList(
                basicActiveUser
        ));
    }
}
