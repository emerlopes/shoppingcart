package br.com.emerlopes.shoppingcart.domain.usecase.impl;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.OrderDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.FindOrderByUsernameUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindOrderByUsernameUseCaseImpl implements FindOrderByUsernameUseCase {

    private final OrderDomainRepository orderDomainRepository;

    public FindOrderByUsernameUseCaseImpl(
            final OrderDomainRepository orderDomainRepository
    ) {
        this.orderDomainRepository = orderDomainRepository;
    }

    @Override
    public List<OrderDomainEntity> execute(
            final String username
    ) {
        return orderDomainRepository.getOrderByUsername(username);
    }
}
