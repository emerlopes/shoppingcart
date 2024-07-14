package br.com.emerlopes.shoppingcart.application.exceptions;

import lombok.Getter;

import java.io.Serial;

@Getter
public class OrderNotFoundByIdException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public OrderNotFoundByIdException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
