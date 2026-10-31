package service;
import model.Order;
import exception.InvalidOrderException;
import exception.InvalidOrderException;
import exception.OutOfStockException;
import model.Order;
import model.OrderItem;
import model.OrderStatus;

public class OrderService {
    //Add the dependencies
    private CustomerService customerService;
    private InventoryService inventoryService;

    public OrderService(CustomerService customerService, InventoryService inventoryService) {
        this.customerService = customerService;
        this.inventoryService = inventoryService;
    }

    //Create order
    public Order createOrder(Order order)
            throws InvalidOrderException {

        if (order == null) {
            throw new InvalidOrderException(
                    "Order cannot be null"
            );
        }

        if (order.getCustomer() == null) {
            throw new InvalidOrderException(
                    "Customer is required"
            );
        }

        if (order.getItems() == null ||
                order.getItems().isEmpty()) {

            throw new InvalidOrderException(
                    "Order must contain at least one item"
            );
        }

        return order;
    }

    //Processing order
    public void processOrder(Order order)
            throws InvalidOrderException, OutOfStockException {

        createOrder(order);

        order.setStatus(OrderStatus.PROCESSING);

        //Check all stock first
        for (OrderItem item : order.getItems()) {

            int productId =
                    item.getProduct().getId();

            int quantity =
                    item.getQuantity();

            int warehouseId =
                    1;

            if (!inventoryService.hasStock(
                    productId,
                    warehouseId,
                    quantity)) {

                order.setStatus(OrderStatus.PENDING);

                throw new OutOfStockException(
                        "Not enough stock for product: "
                                + item.getProduct().getName()
                );
            }
        }

        //Remove stock
        for (OrderItem item : order.getItems()) {

            int productId =
                    item.getProduct().getId();

            int quantity =
                    item.getQuantity();

            int warehouseId =
                    1;

            inventoryService.removeStock(
                    productId,
                    warehouseId,
                    quantity
            );
        }

        //Complete order
        order.setStatus(OrderStatus.COMPLETED);
    }
}
