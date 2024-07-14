package br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.impl;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.ShoppingCartDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart.GetAllShoppingCartUseCase;
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
