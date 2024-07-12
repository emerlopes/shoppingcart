package br.com.emerlopes.shoppingcart.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ShoppingCartDomainEntity {
    private String username;
    private List<ProductDomainEntity> products;
    private BigDecimal total;
}
