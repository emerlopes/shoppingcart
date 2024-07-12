package br.com.emerlopes.shoppingcart.application.entrypoint.rest;

import br.com.emerlopes.shoppingcart.application.shared.CustomResponseDTO;
import br.com.emerlopes.shoppingcart.domain.usecase.CreateShoppingCartByUsernameUseCase;
import br.com.emerlopes.shoppingcart.domain.usecase.GetShoppingCartByUsernameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase;
    private final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase;

    public ShoppingCartController(
            final CreateShoppingCartByUsernameUseCase createShoppingCartByUsernameUseCase,
            final GetShoppingCartByUsernameUseCase getShoppingCartByUsernameUseCase
    ) {
        this.createShoppingCartByUsernameUseCase = createShoppingCartByUsernameUseCase;
        this.getShoppingCartByUsernameUseCase = getShoppingCartByUsernameUseCase;
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
}
