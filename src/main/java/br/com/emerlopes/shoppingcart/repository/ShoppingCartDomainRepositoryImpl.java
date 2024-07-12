package br.com.emerlopes.shoppingcart.repository;

import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ProductEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ShoppingCartEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.repository.ProductRepository;
import br.com.emerlopes.shoppingcart.infrastructure.database.repository.ShoppingCartRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartDomainRepositoryImpl implements ShoppingCartDomainRepository {

    private final static Logger logger = org.slf4j.LoggerFactory.getLogger(ShoppingCartDomainRepositoryImpl.class);
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
        final var isShoppingCartAlreadyCreated = shoppingCartRepository.existsById(username);

        if (isShoppingCartAlreadyCreated) {
            logger.info("Shopping cart already created for user {}", username);
            return this.getShoppingCart(username);
        }

        final var shoppingCartEntity = shoppingCartRepository.save(this.toEntity(username));

        logger.info("Shopping cart created for user {}", username);

        return this.toDomainEntity(shoppingCartEntity);
    }

    @Override
    public ShoppingCartDomainEntity getShoppingCart(
            final String username
    ) {
        final var shoppingCartEntity = shoppingCartRepository.findById(username);

        if (shoppingCartEntity.isEmpty()) {
            logger.info("Shopping cart not found for user {}", username);
            return this.createShoppingCartByUserName(username);
        }

        logger.info("Shopping cart found for user {}", username);

        return this.toDomainEntity(shoppingCartEntity.get());
    }

    private ShoppingCartDomainEntity toDomainEntity(
            final ShoppingCartEntity shoppingCart
    ) {
        return ShoppingCartDomainEntity.builder()
                .username(shoppingCart.getUsername())
                .products(shoppingCart.getProducts().stream().map(this::toDomainEntity).toList())
                .total(shoppingCart.getTotal())
                .build();
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
