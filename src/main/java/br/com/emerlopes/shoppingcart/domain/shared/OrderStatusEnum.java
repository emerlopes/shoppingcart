package br.com.emerlopes.shoppingcart.domain.shared;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    PENDING("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    CANCELED("CANCELED");

    private final String status;

    OrderStatusEnum(
            final String status
    ) {
        this.status = status;
    }

    public static OrderStatusEnum fromString(
            final String status
    ) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getStatus().equalsIgnoreCase(status)) {
                return orderStatusEnum;
            }
        }
        return null;
    }

}
