package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.RemoveProductFromShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

@Service
public class RemoveProductFromShoppingCartByUsernameUseCaseImpl implements RemoveProductFromShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartDomainRepository;

    public RemoveProductFromShoppingCartByUsernameUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartDomainRepository
    ) {
        this.shoppingCartDomainRepository = shoppingCartDomainRepository;
    }

    @Override
    public ShoppingCartDomainEntity execute(
            final ProductDomainEntity productDomainEntity
    ) {
        return shoppingCartDomainRepository.removeProductFromShoppingCart(productDomainEntity);
    }
}
