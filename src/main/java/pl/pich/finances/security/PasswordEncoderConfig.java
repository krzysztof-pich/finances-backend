package pl.pich.finances.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return BCrypt.hashpw(charSequence.toString(), BCrypt.gensalt(4));
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return BCrypt.checkpw(charSequence.toString(), s);
//            }
//        };
//    }
}
