package br.com.emerlopes.shoppingcart.repository;

import br.com.emerlopes.shoppingcart.application.exceptions.OrderNotFoundByIdException;
import br.com.emerlopes.shoppingcart.domain.entity.OrderDomainEntity;
import br.com.emerlopes.shoppingcart.domain.entity.ProductDomainEntity;
import br.com.emerlopes.shoppingcart.domain.repository.OrderDomainRepository;
import br.com.emerlopes.shoppingcart.domain.shared.OrderStatusEnum;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.OrderEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.entity.ProductEntity;
import br.com.emerlopes.shoppingcart.infrastructure.database.repository.OrderRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderDomainRepositoryImpl implements OrderDomainRepository {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderDomainRepositoryImpl.class);
    private final OrderRepository orderRepository;

    public OrderDomainRepositoryImpl(
            final OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDomainEntity saveOrder(
            final OrderDomainEntity order
    ) {

        final var orderEntity = OrderEntity.builder()
                .username(order.getUsername())
                .total(order.getTotal())
                .status(order.getStatus().getStatus())
                .createdAt(LocalDateTime.now())
                .build();

        final var products = order.getProducts().stream()
                .map(product -> ProductEntity.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .order(orderEntity)
                        .build())
                .toList();

        orderEntity.setProducts(products);

        final var savedOrder = orderRepository.save(orderEntity);

        logger.info("Order saved: {}", savedOrder.getOrderId());

        return OrderDomainEntity.builder()
                .id(savedOrder.getOrderId())
                .username(savedOrder.getUsername())
                .products(this.toDomainEntity(savedOrder.getProducts()))
                .total(savedOrder.getTotal())
                .status(OrderStatusEnum.fromString(savedOrder.getStatus()))
                .createdAt(savedOrder.getCreatedAt())
                .build();
    }

    @Override
    public OrderDomainEntity updateOrderStatus(
            final OrderDomainEntity order
    ) {

        final var orderId = order.getId();
        final var orderStatus = order.getStatus();
        final var orderEntity = orderRepository.findById(orderId);

        if (orderEntity.isEmpty()) {
            throw new OrderNotFoundByIdException("Order not found", "ORDER_NOT_FOUND");
        }

        final var orderToUpdate = orderEntity.get();

        orderToUpdate.setStatus(orderStatus.getStatus());

        final var updatedOrder = orderRepository.save(orderToUpdate);

        logger.info("Order updated: {}", updatedOrder.getOrderId());

        return OrderDomainEntity.builder()
                .id(updatedOrder.getOrderId())
                .username(updatedOrder.getUsername())
                .products(this.toDomainEntity(updatedOrder.getProducts()))
                .total(updatedOrder.getTotal())
                .status(OrderStatusEnum.fromString(updatedOrder.getStatus()))
                .createdAt(updatedOrder.getCreatedAt())
                .build();
    }

    @Override
    public OrderDomainEntity getOrderById(
            final Long id
    ) {
        final var orderEntity = orderRepository.findById(id);

        if (orderEntity.isEmpty()) {
            throw new OrderNotFoundByIdException("Order not found", "ORDER_NOT_FOUND");
        }

        final var order = orderEntity.get();
        final var products = order.getProducts();

        logger.info("Order found by id: {}", order.getOrderId());

        return OrderDomainEntity.builder()
                .id(order.getOrderId())
                .username(order.getUsername())
                .products(this.toDomainEntity(products))
                .total(order.getTotal())
                .status(OrderStatusEnum.fromString(order.getStatus()))
                .createdAt(order.getCreatedAt())
                .build();
    }

    @Override
    public List<OrderDomainEntity> getOrderByUsername(
            final String username
    ) {
        final var orderEntity = orderRepository.findByUsername(username);

        if (orderEntity.isEmpty()) {
            throw new OrderNotFoundByIdException("Order not found", "ORDER_NOT_FOUND");
        }

        final var order = orderEntity.get();

        logger.info("Order found by username: {}", username);

        return order.stream()
                .map(or -> OrderDomainEntity.builder()
                        .id(or.getOrderId())
                        .username(or.getUsername())
                        .products(this.toDomainEntity(or.getProducts()))
                        .total(or.getTotal())
                        .status(OrderStatusEnum.fromString(or.getStatus()))
                        .createdAt(or.getCreatedAt())
                        .build()
                )
                .toList();
    }

    private List<ProductDomainEntity> toDomainEntity(
            final List<ProductEntity> products
    ) {
        return products.stream()
                .map(product -> ProductDomainEntity.builder()
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .build())
                .toList();
    }
}
