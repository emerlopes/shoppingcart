package br.com.emerlopes.shoppingcart.infrastructure.database.repository;

import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ProductEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    void deleteByName(String name);
    List<ProductEntity> findByShoppingCartUsername(String username);
}