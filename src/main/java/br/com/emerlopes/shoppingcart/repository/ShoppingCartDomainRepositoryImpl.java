package br.com.emerlopes.shoppingcart.repository;

import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ProductEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ShoppingCartEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.repository.ProductRepository;
import br.com.emerlopes.shoppingcart.infrastructure.database.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartDomainRepositoryImpl implements ShoppingCartDomainRepository {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public ShoppingCartDomainRepositoryImpl(
            final ShoppingCartRepository shoppingCartRepository,
            final ProductRepository productRepository
    ) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ShoppingCartDomainEntity createShoppingCartByUserName(
            final String username
    ) {
        final var shoppingCartEntity = shoppingCartRepository.save(this.toEntity(username));
        return ShoppingCartDomainEntity.builder()
                .username(shoppingCartEntity.getUsername())
                .products(shoppingCartEntity.getProducts().stream().map(this::toDomainEntity).toList())
                .total(shoppingCartEntity.getTotal())
                .build();
    }

    @Override
    public ShoppingCartDomainEntity getShoppingCart(
            final String username
    ) {
        return shoppingCartRepository.findById(username)
                .map(shoppingCartEntity -> ShoppingCartDomainEntity.builder()
                        .username(shoppingCartEntity.getUsername())
                        .products(shoppingCartEntity.getProducts().stream().map(this::toDomainEntity).toList())
                        .build())
                .orElseThrow();
    }

    private ShoppingCartEntity toEntity(
            final ShoppingCartDomainEntity shoppingCart
    ) {
        return ShoppingCartEntity.builder()
                .username(shoppingCart.getUsername())
                .products(List.of())
                .total(BigDecimal.ZERO)
                .build();
    }

    private ShoppingCartEntity toEntity(
            final String username
    ) {
        return ShoppingCartEntity.builder()
                .username(username)
                .products(List.of())
                .total(BigDecimal.ZERO)
                .build();
    }

    private ProductDomainEntity toDomainEntity(
            final ProductEntity product
    ) {
        return ProductDomainEntity.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
