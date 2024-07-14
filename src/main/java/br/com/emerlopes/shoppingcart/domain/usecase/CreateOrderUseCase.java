package br.com.emerlopes.shoppingcart.domain.usecase;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.shared.IExecuteArgs;

public interface CreateOrderUseCase extends IExecuteArgs<OrderDomainEntity, OrderDomainEntity> {
}
