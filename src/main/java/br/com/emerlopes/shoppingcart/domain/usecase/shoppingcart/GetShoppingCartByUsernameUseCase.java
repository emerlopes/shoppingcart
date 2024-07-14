package br.com.emerlopes.shoppingcart.domain.usecase.shoppingcart;

import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.shared.IExecuteArgs;

public interface GetShoppingCartByUsernameUseCase extends IExecuteArgs<ShoppingCartDomainEntity, String> {
}
