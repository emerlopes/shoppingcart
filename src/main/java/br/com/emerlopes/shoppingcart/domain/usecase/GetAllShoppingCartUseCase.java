package br.com.emerlopes.shoppingcart.domain.usecase;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.shared.IExecuteArgs;

import java.util.List;

public interface GetAllShoppingCartUseCase extends IExecuteArgs<List<ShoppingCartDomainEntity>, Void> {
}
