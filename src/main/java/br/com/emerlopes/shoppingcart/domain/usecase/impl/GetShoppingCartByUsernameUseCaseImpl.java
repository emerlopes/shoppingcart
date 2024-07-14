package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.GetShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

@Service
public class GetShoppingCartByUsernameUseCaseImpl implements GetShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartRepository;

    public GetShoppingCartByUsernameUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartRepository
    ) {
        this.shoppingCartRepository = shoppingCartRepository;
    }


    @Override
    public ShoppingCartDomainEntity execute(
            final String username
    ) {
        return shoppingCartRepository.getShoppingCart(username);
    }
}
