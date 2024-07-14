package br.com.emerlopes.shoppingcart.domain.entity;

import br.com.emerlopes.shoppingcart.domain.shared.OrderStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDomainEntity {
    private Long id;
    private String username;
    private List<ProductDomainEntity> products;
    private BigDecimal total;
    private OrderStatusEnum status;
    private LocalDateTime createdAt;
}
