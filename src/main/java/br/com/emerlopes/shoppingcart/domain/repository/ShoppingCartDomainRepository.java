package br.com.emerlopes.shoppingcart.domain.repository;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;

public interface ShoppingCartDomainRepository {
    ShoppingCartDomainEntity createShoppingCartByUserName(
            final String username
    );

    ShoppingCartDomainEntity getShoppingCart(
            final String username
    );
}
