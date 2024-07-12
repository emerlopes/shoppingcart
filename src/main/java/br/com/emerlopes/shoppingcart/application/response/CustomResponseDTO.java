package br.com.emerlopes.shoppingcart.application.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomResponseDTO<T> {
    private T data;
}