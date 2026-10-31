package dao;

import model.Product;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    //Add product
    public void addProduct(Product product) throws SQLException {

        String sql = """
                INSERT INTO products (name, category, price)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.getCategory());
            statement.setDouble(3, product.getPrice());

            statement.executeUpdate();
        }
    }
//Get product
    public Product getProduct(int id) throws SQLException {

        String sql = """
            SELECT id, name, category, price
            FROM products
            WHERE id = ?
            """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    return new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("price")
                    );
                }
            }
        }

        return null;
    }

//Read product
    public List<Product> getAllProducts() throws SQLException {

    String sql = """
            SELECT id, name, category, price
            FROM products
            """;

    List<Product> products = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Product product = new Product(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("category"),
                    resultSet.getDouble("price")
            );

            products.add(product);
        }
    }

    return products;
}

//update product
public void updateProduct(Product product) throws SQLException {

    String sql = """
            UPDATE products
            SET name = ?, category = ?, price = ?
            WHERE id = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, product.getName());
        statement.setString(2, product.getCategory());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getId());

        statement.executeUpdate();
    }
}

//Delete product
public void deleteProduct(int id) throws SQLException {

    String sql = """
            DELETE FROM products
            WHERE id = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id);

        statement.executeUpdate();
    }
}
}