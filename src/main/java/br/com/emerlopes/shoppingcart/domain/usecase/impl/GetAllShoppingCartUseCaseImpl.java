package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.GetAllShoppingCartUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.GetShoppingCartByUsernameUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllShoppingCartUseCaseImpl implements GetAllShoppingCartUseCase {

    private final ShoppingCartDomainRepository shoppingCartRepository;

    public GetAllShoppingCartUseCaseImpl(
            final ShoppingCartDomainRepository shoppingCartRepository
    ) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    public List<ShoppingCartDomainEntity> execute(Void domainObject) {
        return shoppingCartRepository.getAllShoppingCarts();
    }
}
