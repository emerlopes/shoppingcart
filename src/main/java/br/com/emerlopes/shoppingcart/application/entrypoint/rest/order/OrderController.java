package br.com.emerlopes.shoppingcart.application.entrypoint.rest.order;

import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ProductDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ShoppingCartResponseDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.order.request.OrderRequestDTO;
import br.com.emerlopes.shoppingcart.application.shared.CustomResponseDTO;
import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.usecase.CreateOrderUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.FindOrderByIdUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.FindOrderByUsernameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final FindOrderByIdUseCase findOrderByIdUseCase;
    private final FindOrderByUsernameUseCase findOrderByUsernameUseCase;

    public OrderController(
            final CreateOrderUseCase createOrderUseCase,
            final FindOrderByIdUseCase findOrderByIdUseCase,
            final FindOrderByUsernameUseCase findOrderByUsernameUseCase
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.findOrderByIdUseCase = findOrderByIdUseCase;
        this.findOrderByUsernameUseCase = findOrderByUsernameUseCase;
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            final @RequestBody OrderRequestDTO orderRequestDTO
    ) {
        final var products = orderRequestDTO.getProducts().stream()
                .map(p -> ProductDomainEntity.builder()
                        .name(p.getName())
                        .description(p.getDescription())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .build()
                ).toList();

        final var executionResult = createOrderUseCase.execute(
                OrderDomainEntity.builder()
                        .username(orderRequestDTO.getUsername())
                        .products(products)
                        .total(products.stream()
                                .map(p -> p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())))
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                        )
                        .build()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(executionResult);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findOrderById(
            final @PathVariable("orderId") Long orderId
    ) {
        final var executionResult = findOrderByIdUseCase.execute(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(executionResult);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<?> findOrderByUsername(
            final @PathVariable("username") String username
    ) {
        final var executionResult = findOrderByUsernameUseCase.execute(username);
        return ResponseEntity.status(HttpStatus.OK).body(executionResult);
    }

    private ResponseEntity<?> getOrderResponseDTO(
            final OrderDomainEntity executionResult
    ) {
        return getResponseEntity(executionResult);
    }

    public static ResponseEntity<?> getResponseEntity(
            final OrderDomainEntity executionResult
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(ShoppingCartResponseDTO.builder()
                        .username(executionResult.getUsername())
                        .products(executionResult.getProducts().stream()
                                .map(p -> ProductDTO.builder()
                                        .name(p.getName())
                                        .description(p.getDescription())
                                        .price(p.getPrice())
                                        .quantity(p.getQuantity())
                                        .build()
                                ).toList())
                        .total(executionResult.getTotal())
                        .build())
        );
    }


}
