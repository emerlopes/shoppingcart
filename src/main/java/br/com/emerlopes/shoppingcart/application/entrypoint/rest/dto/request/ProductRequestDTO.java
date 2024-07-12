package br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
