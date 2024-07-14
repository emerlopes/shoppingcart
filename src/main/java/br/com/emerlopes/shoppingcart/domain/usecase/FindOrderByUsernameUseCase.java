package br.com.emerlopes.shoppingcart.domain.usecase;

import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.shared.IExecuteArgs;

import java.util.List;

public interface FindOrderByUsernameUseCase extends IExecuteArgs<List<OrderDomainEntity>, String> {
}
