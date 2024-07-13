package br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ShoppingCartResponseDTO {
    private String username;
    private List<ProductDTO> products;
    private BigDecimal total;
}