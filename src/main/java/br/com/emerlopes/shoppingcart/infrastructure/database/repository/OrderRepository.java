package br.com.emerlopes.shoppingcart.infrastructure.database.repository;

import br.com.emerlopes.shoppingcart.infrastructure.database.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<List<OrderEntity>> findByUsername(
            final String username
    );
}
