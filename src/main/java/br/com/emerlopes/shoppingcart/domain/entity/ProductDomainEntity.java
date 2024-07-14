package br.com.emerlopes.shoppingcart.domain.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDomainEntity {
    private String username;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
