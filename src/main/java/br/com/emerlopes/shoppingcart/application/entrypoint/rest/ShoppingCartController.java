package br.com.emerlopes.shoppingcart.application.entrypoint.rest;

import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.request.ProductRequestDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ProductDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ProductResponseDTO;
import br.com.emerlopes.shoppingcart.application.shared.CustomResponseDTO;
import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.usecase.AddProductToShoppingCartByUsernameUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.CreateShoppingCartByUsernameUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.GetAllShoppingCartUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.GetShoppingCartByUsernameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase;
    private final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase;
    private final AddProductToShoppingCartByUsernameUseCase addProductToShoppingCartByUsernameUseCase;
    private final GetAllShoppingCartUseCase getAllShoppingCartUseCase;

    public ShoppingCartController(
            final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase,
            final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase,
            final AddProductToShoppingCartByUsernameUseCase addProductToShoppingCartByUsernameUseCase,
            final GetAllShoppingCartUseCase getAllShoppingCartUseCase
    ) {
        this.createShoppingCartByUsernameUseCase = createShoppingCartByUsernameUseCase;
        this.getShoppingCartByUsernameUseCase = getShoppingCartByUsernameUseCase;
        this.addProductToShoppingCartByUsernameUseCase = addProductToShoppingCartByUsernameUseCase;
        this.getAllShoppingCartUseCase = getAllShoppingCartUseCase;
    }

    @PostMapping("/register/{username}")
    public ResponseEntity<?> registerShoppingCart(
            final @PathVariable("username") String username
    ) {
        final var executionResult = createShoppingCartByUsernameUseCase.execute(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(executionResult)
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getShoppingCart(
            final @PathVariable("username") String username
    ) {
        final var executionResult = getShoppingCartByUsernameUseCase.execute(username);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(executionResult)
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllShoppingCarts() {
        final var executionResult = getAllShoppingCartUseCase.execute(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(executionResult)
        );
    }

    @PostMapping("/add-product/{username}")
    public ResponseEntity<?> addProductToShoppingCart(
            final @PathVariable("username") String username,
            final @RequestBody ProductRequestDTO productRequestDTO
    ) {

        final var executionResult = addProductToShoppingCartByUsernameUseCase.execute(
                ProductDomainEntity.builder()
                        .username(username)
                        .name(productRequestDTO.getName())
                        .description(productRequestDTO.getDescription())
                        .price(productRequestDTO.getPrice())
                        .quantity(productRequestDTO.getQuantity())
                        .build()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(
                        ProductResponseDTO.builder()
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
                                .build()
                )
        );
    }
}
