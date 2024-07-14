package br.com.emerlopes.shoppingcart.domain.usecase.order.impl;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.OrderDomainRepository;
import br.com.emerlopes.shoppingcart.domain.usecase.order.UpdateOrderStatusUseCase;
import org.springframework.stereotype.Service;

@Service
public class UpdateOrderStatusUseCaseImpl implements UpdateOrderStatusUseCase {

    private final OrderDomainRepository orderDomainRepository;

    public UpdateOrderStatusUseCaseImpl(
            final OrderDomainRepository orderDomainRepository
    ) {
        this.orderDomainRepository = orderDomainRepository;
    }

    @Override
    public OrderDomainEntity execute(
            final OrderDomainEntity orderDomainEntity
    ) {
        return orderDomainRepository.updateOrderStatus(orderDomainEntity);
    }
}
