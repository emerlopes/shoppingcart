package br.com.emerlopes.shoppingcart.application.exceptions;

import lombok.Getter;

import javax.naming.AuthenticationException;
import java.io.Serial;

@Getter
public class ShoppingCartNotFoundException extends AuthenticationException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public ShoppingCartNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}