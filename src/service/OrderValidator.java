package service;

import exception.InvalidOrderException;

public class OrderValidator {
    public void validateQuantity(int quantity)
            throws InvalidOrderException {

        if (quantity <= 0) {
            throw new InvalidOrderException(
                    "Order quantity must be greater than zero"
            );
        }

        System.out.println("Order quantity is valid.");
    }
}
