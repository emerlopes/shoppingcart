package br.com.emerlopes.shoppingcart.application.entrypoint.rest;

import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.request.ProductRequestDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ProductDTO;
import br.com.emerlopes.shoppingcart.application.entrypoint.rest.dto.response.ProductResponseDTO;
import br.com.emerlopes.shoppingcart.application.exceptions.UsernameNotFoundException;
import br.com.emerlopes.shoppingcart.application.shared.CustomResponseDTO;
import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ShoppingCartDomainEntity;
import br.com.emerlopes.shoppingcart.domain.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase;
    private final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase;
    private final AddProductToShoppingCartByUsernameUseCase addProductToShoppingCartByUsernameUseCase;
    private final GetAllShoppingCartUseCase getAllShoppingCartUseCase;
    private final DetelteShoppingCartByUsernameUseCase deleteShoppingCartByUsernameUseCase;

    public ShoppingCartController(
            final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase,
            final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase,
            final AddProductToShoppingCartByUsernameUseCase addProductToShoppingCartByUsernameUseCase,
            final GetAllShoppingCartUseCase getAllShoppingCartUseCase,
            final DetelteShoppingCartByUsernameUseCase deleteShoppingCartByUsernameUseCase
    ) {
        this.createShoppingCartByUsernameUseCase = createShoppingCartByUsernameUseCase;
        this.getShoppingCartByUsernameUseCase = getShoppingCartByUsernameUseCase;
        this.addProductToShoppingCartByUsernameUseCase = addProductToShoppingCartByUsernameUseCase;
        this.getAllShoppingCartUseCase = getAllShoppingCartUseCase;
        this.deleteShoppingCartByUsernameUseCase = deleteShoppingCartByUsernameUseCase;
    }

    @PostMapping("/register/{username}")
    public ResponseEntity<?> registerShoppingCart(
            final @PathVariable("username") String username
    ) {
        final var executionResult = createShoppingCartByUsernameUseCase.execute(username);
        return getShoppingCartResponseDTO(executionResult);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getShoppingCart(
            final @PathVariable("username") String username
    ) {
        final var executionResult = getShoppingCartByUsernameUseCase.execute(username);
        return getShoppingCartResponseDTO(executionResult);
    }

    @GetMapping
    public ResponseEntity<?> getAllShoppingCarts() throws UsernameNotFoundException {
        final var executionResult = getAllShoppingCartUseCase.execute(null);
        return getShoppingCartResponseDTO(executionResult);
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

        return getShoppingCartResponseDTO(executionResult);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteShoppingCart(
            final @PathVariable("username") String username
    ) {
        deleteShoppingCartByUsernameUseCase.execute(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private ResponseEntity<?> getShoppingCartResponseDTO(
            final ShoppingCartDomainEntity executionResult
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(ProductResponseDTO.builder()
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

    private ResponseEntity<?> getShoppingCartResponseDTO(
            final List<ShoppingCartDomainEntity> executionResult
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new CustomResponseDTO<>().setData(executionResult.stream()
                        .map(s -> ProductResponseDTO.builder()
                                .username(s.getUsername())
                                .products(s.getProducts().stream()
                                        .map(p -> ProductDTO.builder()
                                                .name(p.getName())
                                                .description(p.getDescription())
                                                .price(p.getPrice())
                                                .quantity(p.getQuantity())
                                                .build()
                                        ).toList())
                                .total(s.getTotal())
                                .build()
                        ).toList())
        );
    }
}
