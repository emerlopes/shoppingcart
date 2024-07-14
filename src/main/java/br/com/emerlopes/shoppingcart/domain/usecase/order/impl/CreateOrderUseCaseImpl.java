package br.com.emerlopes.shoppingcart.domain.usecase.order.impl;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.OrderDomainRepository;
import br.com.emerlopes.shoppingcart.domain.shared.OrderStatusEnum;
import br.com.emerlopes.shoppingcart.domain.usecase.order.CreateOrderUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

    private final OrderDomainRepository orderDomainRepository;

    public CreateOrderUseCaseImpl(
            final OrderDomainRepository orderDomainRepository
    ) {
        this.orderDomainRepository = orderDomainRepository;
    }

    @Override
    public OrderDomainEntity execute(
            final OrderDomainEntity orderDomainEntity
    ) {

        orderDomainEntity.setStatus(OrderStatusEnum.PENDING);

        return orderDomainRepository.saveOrder(orderDomainEntity);
    }
}
