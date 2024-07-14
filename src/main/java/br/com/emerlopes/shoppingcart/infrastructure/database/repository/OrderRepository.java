package br.com.emerlopes.shoppingcart.infrastructure.database.repository;

import br.com.emerlopes.shoppingcart.infrastructure.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
