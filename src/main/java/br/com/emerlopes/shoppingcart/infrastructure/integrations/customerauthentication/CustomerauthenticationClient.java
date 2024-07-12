package br.com.emerlopes.shoppingcart.infrastructure.integrations.customerauthentication;

import br.com.emerlopes.shoppingcart.application.response.CustomResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@FeignClient(name = "customerauthentication", url = "${application.customerauthentication.host}")
public interface CustomerauthenticationClient {

    @GetMapping("/auth/validate")
    Optional<CustomResponseDTO<CustomerauthenticationResponseDTO>> getCustomerAuthentication(
            final @RequestHeader("Authorization") String authorization
    );

    @GetMapping("/users/{login}")
    Optional<CustomResponseDTO<CustomerauthenticationResponseDTO>> getCustomerByLogin(
            final @RequestHeader(value = "Authorization") String authorization,
            final @PathVariable("login") String login
    );

}
