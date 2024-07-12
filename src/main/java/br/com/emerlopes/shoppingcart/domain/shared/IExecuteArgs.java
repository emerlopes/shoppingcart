package br.com.emerlopes.shoppingcart.domain.shared;

public interface IExecuteArgs<T, J> {
    T execute(J domainObject);
}
