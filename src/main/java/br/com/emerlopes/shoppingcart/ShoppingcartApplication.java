package br.com.emerlopes.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients({"br.com.emerlopes.shoppingcart.infrastructure.integrations"})
public class ShoppingcartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingcartApplication.class, args);
    }

}
