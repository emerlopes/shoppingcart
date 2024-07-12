package br.com.emerlopes.shoppingcart.infrastructure.security;


import br.com.emerlopes.shoppingcart.infrastructure.integrations.customerauthentication.CustomerauthenticationClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {


    private final CustomerauthenticationClient customerauthenticationClient;

    public AuthorizationService(
            final CustomerauthenticationClient customerauthenticationClient
    ) {
        this.customerauthenticationClient = customerauthenticationClient;
    }


    @Override
    public UserDetails loadUserByUsername(
            final String username
    ) throws UsernameNotFoundException {
        return User
                .withUsername(username)
                .password("password")
                .authorities("ROLE_USER")
                .build();
    }
}
