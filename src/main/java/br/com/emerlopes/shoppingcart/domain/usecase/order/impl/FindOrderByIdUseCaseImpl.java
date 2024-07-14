package br.com.emerlopes.shoppingcart.domain.usecase.order.impl;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.OrderDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.order.FindOrderByIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class FindOrderByIdUseCaseImpl implements FindOrderByIdUseCase {

    private final OrderDomainRepository orderDomainRepository;

    public FindOrderByIdUseCaseImpl(
            final OrderDomainRepository orderDomainRepository
    ) {
        this.orderDomainRepository = orderDomainRepository;
    }

    @Override
    public OrderDomainEntity execute(
            final Long id
    ) {
        return orderDomainRepository.getOrderById(id);
    }
}
