package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.AddProductToShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AddProductToShoppingCartByUsernameUseCaseImpl implements AddProductToShoppingCartByUsernameUseCase {

    private final ShoppingCartDomainRepository shoppingCartDomainRepository;

    public AddProductToShoppingCartByUsernameUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartDomainRepository
    ) {
        this.shoppingCartDomainRepository = shoppingCartDomainRepository;
    }

    @Override
    public ShoppingCartDomainEntity execute(
            final ProductDomainEntity productDomainEntity
    ) {
        final var shoppingCart = shoppingCartDomainRepository.addProductToShoppingCart(productDomainEntity);

        final var total = shoppingCart.getProducts().stream()
                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        shoppingCart.setTotal(total);

        return shoppingCart;
    }
}
