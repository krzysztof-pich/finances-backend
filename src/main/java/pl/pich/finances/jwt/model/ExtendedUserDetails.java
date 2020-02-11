package pl.pich.finances.jwt.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import java.util.Collection;

public class ExtendedUserDetails extends User {
    private final pl.pich.finances.user.model.User dbUser;

    public ExtendedUserDetails(String username, String password, pl.pich.finances.user.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.dbUser = user;
    }

    public pl.pich.finances.user.model.User getDbUser() {
        return dbUser;
    }
}
