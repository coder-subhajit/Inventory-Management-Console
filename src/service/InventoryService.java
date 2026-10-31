package service;

import model.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private List<Inventory> inventories;

    public InventoryService() {
        inventories = new ArrayList<>();
    }

    //Add inventory
    public void addInventory(Inventory inventory) {

        inventories.add(inventory);

        System.out.println(
                "Inventory added successfully."
        );
    }

    //Find inventory
    public Inventory findInventory(int productId, int warehouseId) {

        for (Inventory inventory : inventories) {

            if (inventory.getProduct().getId() == productId
                    && inventory.getWarehouse().getId() == warehouseId) {

                return inventory;
            }
        }

        return null;
    }

    //Check stock
    public boolean hasStock(
            int productId,
            int warehouseId,
            int requiredQuantity) {

        Inventory inventory =
                findInventory(productId, warehouseId);

        if (inventory == null) {
            return false;
        }

        return inventory.getQuantity() >= requiredQuantity;
    }

    //Add stock
    public void addStock(
            int productId,
            int warehouseId,
            int quantity) {

        Inventory inventory =
                findInventory(productId, warehouseId);

        if (inventory == null) {

            System.out.println(
                    "Inventory not found."
            );

            return;
        }

        inventory.setQuantity(
                inventory.getQuantity() + quantity
        );

        System.out.println(
                "Stock added successfully."
        );
    }

    //Remove stock
    public void removeStock(
            int productId,
            int warehouseId,
            int quantity) {

        Inventory inventory =
                findInventory(productId, warehouseId);

        if (inventory == null) {

            System.out.println(
                    "Inventory not found."
            );

            return;
        }

        if (inventory.getQuantity() < quantity) {

            System.out.println(
                    "Not enough stock."
            );

            return;
        }

        inventory.setQuantity(
                inventory.getQuantity() - quantity
        );

        System.out.println(
                "Stock removed successfully."
        );
    }

    //Display inventory
    public void displayInventory() {

        for (Inventory inventory : inventories) {

            System.out.println(
                    "Product: "
                            + inventory.getProduct().getName()
                            + " | Warehouse: "
                            + inventory.getWarehouse().getName()
                            + " | Quantity: "
                            + inventory.getQuantity()
            );
        }
    }
}