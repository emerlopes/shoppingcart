package br.com.emerlopes.shoppingcart.infrastructure.integrations.customerauthentication;

import lombok.Data;

@Data
public class CustomerauthenticationResponseDTO {
    private String username;
    private String role;
}
