package br.com.emerlopes.shoppingcart.infrastructure.database.repository;

import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, String> {
}
