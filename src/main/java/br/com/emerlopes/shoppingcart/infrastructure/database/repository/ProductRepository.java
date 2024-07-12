package br.com.emerlopes.shoppingcart.infrastructure.database.repository;

import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}