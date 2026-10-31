import dao.CustomerDAO;
import exception.InvalidOrderException;
import exception.OutOfStockException;
import model.Customer;
import model.Inventory;
import model.Order;
import model.OrderItem;
import model.Product;
import model.Warehouse;
import service.CustomerService;
import service.InventoryService;
import service.OrderService;

public class Main {

    public static void main(String[] args) {

        try {

            // ==========================================
            // 1. CREATE SERVICES
            // ==========================================

            CustomerDAO customerDAO =
                    new CustomerDAO();

            CustomerService customerService =
                    new CustomerService(customerDAO);

            InventoryService inventoryService =
                    new InventoryService();

            OrderService orderService =
                    new OrderService(
                            customerService,
                            inventoryService
                    );


            // ==========================================
            // 2. CREATE PRODUCTS
            // ==========================================

            Product laptop =
                    new Product(
                            1,
                            "Laptop",
                            "Electronics",
                            60000
                    );

            Product mouse =
                    new Product(
                            2,
                            "Mouse",
                            "Electronics",
                            800
                    );


            // ==========================================
            // 3. GET CUSTOMER FROM DATABASE
            // ==========================================

            Customer customer =
                    customerService.getCustomer(1);

            if (customer == null) {

                System.out.println(
                        "Customer not found."
                );

                return;
            }

            System.out.println(
                    "Customer loaded: "
                            + customer.getName()
            );


            // ==========================================
            // 4. CREATE WAREHOUSE
            // ==========================================

            Warehouse warehouse =
                    new Warehouse(
                            1,
                            "Main Warehouse",
                            "Kolkata"
                    );


            // ==========================================
            // 5. CREATE INVENTORY
            // ==========================================

            Inventory laptopInventory =
                    new Inventory(
                            laptop,
                            warehouse,
                            10
                    );

            Inventory mouseInventory =
                    new Inventory(
                            mouse,
                            warehouse,
                            5
                    );

            inventoryService.addInventory(
                    laptopInventory
            );

            inventoryService.addInventory(
                    mouseInventory
            );


            // ==========================================
            // 6. CREATE ORDER
            // ==========================================

            Order order =
                    new Order(
                            1001,
                            customer
                    );


            // ==========================================
            // 7. CREATE ORDER ITEMS
            // ==========================================

            OrderItem laptopItem =
                    new OrderItem(
                            laptop,
                            2,
                            laptop.getPrice()
                    );

            OrderItem mouseItem =
                    new OrderItem(
                            mouse,
                            10,
                            mouse.getPrice()
                    );


            // ==========================================
            // 8. ADD ITEMS TO ORDER
            // ==========================================

            order.addItem(laptopItem);
            order.addItem(mouseItem);


            // ==========================================
            // 9. SHOW ORDER BEFORE PROCESSING
            // ==========================================

            System.out.println();

            System.out.println(
                    "===== ORDER BEFORE PROCESSING ====="
            );

            System.out.println(
                    "Order ID: "
                            + order.getOrderId()
            );

            System.out.println(
                    "Customer: "
                            + order.getCustomer().getName()
            );

            System.out.println(
                    "Total: ₹"
                            + order.calculateTotal()
            );

            System.out.println(
                    "Status: "
                            + order.getStatus()
            );


            // ==========================================
            // 10. PROCESS ORDER
            // ==========================================

            System.out.println();

            System.out.println(
                    "===== PROCESSING ORDER ====="
            );

            try {

                orderService.processOrder(order);

                System.out.println(
                        "Order processed successfully."
                );

            } catch (InvalidOrderException |
                     OutOfStockException e) {

                System.out.println(
                        "Order failed: "
                                + e.getMessage()
                );
            }


            // ==========================================
            // 11. SHOW FINAL STATUS
            // ==========================================

            System.out.println();

            System.out.println(
                    "===== FINAL ORDER STATUS ====="
            );

            System.out.println(
                    "Status: "
                            + order.getStatus()
            );


            // ==========================================
            // 12. SHOW FINAL INVENTORY
            // ==========================================

            System.out.println();

            System.out.println(
                    "===== FINAL INVENTORY ====="
            );

            inventoryService.displayInventory();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}