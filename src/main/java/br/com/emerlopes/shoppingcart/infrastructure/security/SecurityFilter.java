package br.com.emerlopes.shoppingcart.infrastructure.security;

import br.com.emerlopes.shoppingcart.application.response.UserRoleEnum;
import br.com.emerlopes.shoppingcart.infrastructure.integrations.customerauthentication.CustomerauthenticationClient;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class SecurityFilter extends OncePerRequestFilter {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TokenService.class);

    private final TokenService tokenService;

    private final CustomerauthenticationClient customerauthenticationClient;


    public SecurityFilter(
            final TokenService tokenService,
            final CustomerauthenticationClient customerauthenticationClient
    ) {
        this.tokenService = tokenService;
        this.customerauthenticationClient = customerauthenticationClient;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws IOException {

        try {
            final var token = getToken(request);

            JWTVerifier verifier = JWT.require(getAlgorithm("mySimpleSecret123"))
                    .withIssuer("API")
                    .build();

            assert token != null;
            final var jwt = verifier.verify(token.replace("Bearer ", ""));
            final var login = jwt.getSubject();
            final var roles = jwt.getClaim("roles").asList(String.class);

            if (roles.toString().contains(UserRoleEnum.ADMIN.name())) {
                final var customerByLogin = customerauthenticationClient.getCustomerByLogin("Bearer " + token, login);

                if (customerByLogin.isEmpty()) {
                    logger.error("User not found for login: {}", login);
                    filterChain.doFilter(request, response);
                    return;
                }

                final var authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                List<String> roless = Arrays.asList(UserRoleEnum.ADMIN.getRole(), UserRoleEnum.USER.getRole());

                final var userDetails = User
                        .withUsername(login)
                        .password("password")
                        .authorities(authorities)
                        .build();

                final var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, roless.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Error logging in: {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(e.getMessage());
        }

    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isBlank() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ", "");
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
