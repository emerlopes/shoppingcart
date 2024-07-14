package br.com.emerlopes.shoppingcart.infrastructure.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
public class TokenService {

    @Value("${spring.security.secret}")
    private String secret;

    public String generateToken(
            final UserDetails userDetails
    ) {
        try {

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();

            return JWT.create()
                    .withIssuer("API")
                    .withSubject(userDetails.getUsername())
                    .withClaim("roles", roles)
                    .withExpiresAt(getExpirationTime())
                    .sign(getAlgorithm(this.secret));

        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error creating token", jwtCreationException);
        }
    }

    private Instant getExpirationTime() {
        return Instant.now().plusSeconds(3600);
    }

    public String validateToken(
            final String token
    ) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(this.secret))
                    .withIssuer("API")
                    .build();
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    public String validateToken(
            final String token,
            final String secret
    ) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm(secret))
                    .withIssuer("API")
                    .build();
            return verifier.verify(token.replace("Bearer ", "")).getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Algorithm getAlgorithm(
            final String secret
    ) {
        try {
            final var trimmedSecret = secret.trim();
            return Algorithm.HMAC256(trimmedSecret);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid secret format", e);
        }
    }
}
