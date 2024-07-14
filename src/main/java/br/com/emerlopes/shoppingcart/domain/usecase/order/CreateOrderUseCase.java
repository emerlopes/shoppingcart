package br.com.emerlopes.shoppingcart.domain.usecase.order;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.shared.IExecuteArgs;

public interface CreateOrderUseCase extends IExecuteArgs<OrderDomainEntity, OrderDomainEntity> {
}
