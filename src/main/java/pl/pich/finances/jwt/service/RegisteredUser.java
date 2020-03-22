package pl.pich.finances.jwt.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pich.finances.jwt.model.ExtendedUserDetails;
import pl.pich.finances.user.model.User;

@Service
public class RegisteredUser {
    public User getUser() {
        ExtendedUserDetails userDetails =
                (ExtendedUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getDbUser();
    }
}
