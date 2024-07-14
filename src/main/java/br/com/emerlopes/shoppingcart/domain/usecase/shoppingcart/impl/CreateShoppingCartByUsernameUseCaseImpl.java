package br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.CreateShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateShoppingCartByUsernameUseCaseImpl implements CreateShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartDomainRepository;

    public CreateShoppingCartByUsernameUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartDomainRepository
    ) {
        this.shoppingCartDomainRepository = shoppingCartDomainRepository;
    }

    @Override
    public ShoppingCartDomainEntity execute(
            final String username
    ) {
        return shoppingCartDomainRepository.createShoppingCartByUserName(username);
    }
}
