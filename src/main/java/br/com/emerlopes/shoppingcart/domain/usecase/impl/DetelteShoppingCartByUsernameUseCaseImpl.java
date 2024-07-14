package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.DetelteShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

@Service
public class DetelteShoppingCartByUsernameUseCaseImpl implements DetelteShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartRepository;

    public DetelteShoppingCartByUsernameUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartRepository
    ) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public Void execute(
            final String username
    ) {
        shoppingCartRepository.deleteShoppingCart(username);
        return null;
    }
}
