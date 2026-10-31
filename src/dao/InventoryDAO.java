package dao;

import model.Inventory;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryDAO {

    //add Inventory
    public void addInventory(Inventory inventory) throws SQLException {

        String sql = """
            INSERT INTO inventory
            (product_id, warehouse_id, quantity)
            VALUES (?, ?, ?)
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(
                    1,
                    inventory.getProduct().getId()
            );

            statement.setInt(
                    2,
                    inventory.getWarehouse().getId()
            );

            statement.setInt(
                    3,
                    inventory.getQuantity()
            );

            statement.executeUpdate();
        }
    }
}