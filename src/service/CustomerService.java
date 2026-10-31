package service;

import dao.CustomerDAO;
import model.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO;

    // Constructor
    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    // Add customer
    public void addCustomer(Customer customer) throws SQLException {

        customerDAO.addCustomer(customer);

        System.out.println(
                "Customer added successfully: "
                        + customer.getName()
        );
    }

    // Get one customer
    public Customer getCustomer(int id) throws SQLException {

        return customerDAO.getCustomer(id);
    }

    // Check customer
    public boolean containsCustomer(int id) throws SQLException {

        return customerDAO.getCustomer(id) != null;
    }

    // Remove customer
    public void removeCustomer(int id) throws SQLException {

        Customer customer = customerDAO.getCustomer(id);

        if (customer == null) {

            System.out.println("Customer not found.");
            return;
        }

        customerDAO.deleteCustomer(id);

        System.out.println(
                "Customer removed successfully."
        );
    }

    // Get all customers
    public List<Customer> getAllCustomers() throws SQLException {

        return customerDAO.getAllCustomers();
    }

    // Show customers
    public void displayCustomers() throws SQLException {

        List<Customer> customers = customerDAO.getAllCustomers();

        for (Customer customer : customers) {

            System.out.println(
                    customer.getId() + " | " +
                            customer.getName() + " | " +
                            customer.getEmail() + " | " +
                            customer.getPhone()
            );
        }
    }

    // Update customer
    public void updateCustomer(Customer customer) throws SQLException {

        customerDAO.updateCustomer(customer);

        System.out.println(
                "Customer updated successfully: "
                        + customer.getName()
        );
    }
}