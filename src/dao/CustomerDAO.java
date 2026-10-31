package dao;

import model.Customer;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    //Add customer
    public void addCustomer(Customer customer) throws SQLException {

        String sql = """
                INSERT INTO customers
                (name, email, phone, address)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getAddress());

            statement.executeUpdate();
        }
    }

//Get customer
public Customer getCustomer(int id) throws SQLException {

    String sql = """
            SELECT id, name, email, phone, address
            FROM customers
            WHERE id = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id);

        try (ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {

                return new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                );
            }
        }
    }

    return null;
}
//get all customer
public List<Customer> getAllCustomers() throws SQLException {

    String sql = """
            SELECT id, name, email, phone, address
            FROM customers
            """;

    List<Customer> customers = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Customer customer = new Customer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone"),
                    resultSet.getString("address")
            );

            customers.add(customer);
        }
    }

    return customers;
}
//update customer
public void updateCustomer(Customer customer) throws SQLException {

    String sql = """
            UPDATE customers
            SET name = ?, email = ?, phone = ?, address = ?
            WHERE id = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, customer.getName());
        statement.setString(2, customer.getEmail());
        statement.setString(3, customer.getPhone());
        statement.setString(4, customer.getAddress());
        statement.setInt(5, customer.getId());

        statement.executeUpdate();
    }
}
//delete customer
public void deleteCustomer(int id) throws SQLException {

    String sql = """
            DELETE FROM customers
            WHERE id = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id);

        statement.executeUpdate();
    }
}
}