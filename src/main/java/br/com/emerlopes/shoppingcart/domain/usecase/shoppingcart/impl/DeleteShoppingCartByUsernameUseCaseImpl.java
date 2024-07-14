package br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.impl;

import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.DeleteShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteShoppingCartByUsernameUseCaseImpl implements DeleteShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartRepository;

    public DeleteShoppingCartByUsernameUseCaseImpl(
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
