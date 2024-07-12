package br.com.emerlopes.shoppingcart.domain.repository;

import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;

import java.util.List;

public interface ShoppingCartDomainRepository {
    ShoppingCartDomainEntity createShoppingCartByUserName(
            final String username
    );

    ShoppingCartDomainEntity getShoppingCart(
            final String username
    );

    ShoppingCartDomainEntity addProductToShoppingCart(
            final ProductDomainEntity productDomainEntity
    );

    List<ShoppingCartDomainEntity> getAllShoppingCarts();
}
