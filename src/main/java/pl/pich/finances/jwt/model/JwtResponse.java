package pl.pich.finances.jwt.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;

    public JwtResponse() {

    }

    public JwtResponse(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
