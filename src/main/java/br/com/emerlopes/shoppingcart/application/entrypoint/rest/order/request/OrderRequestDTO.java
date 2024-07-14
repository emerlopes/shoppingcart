package br.com.emerlopes.shoppingcart.application.entrypoint.rest.order.request;

import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.request.ProductRequestDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequestDTO {
    private String username;
    private List<ProductRequestDTO> products;
    private String status;
}
