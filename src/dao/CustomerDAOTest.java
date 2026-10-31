package dao;

import model.Customer;

public class CustomerDAOTest {

    public static void main(String[] args) {

        try {

            CustomerDAO customerDAO = new CustomerDAO();

            // =========================
            // GET ONE CUSTOMER
            // =========================

            Customer customer = customerDAO.getCustomer(1);

            if (customer != null) {

                System.out.println("Customer found!");

                System.out.println(
                        customer.getId() + " | " +
                                customer.getName() + " | " +
                                customer.getEmail() + " | " +
                                customer.getPhone() + " | " +
                                customer.getAddress()
                );
// DELETE CUSTOMER

                customerDAO.deleteCustomer(2);

                System.out.println("Customer deleted successfully!");
            } else {

                System.out.println("Customer not found.");
            }

            System.out.println("===== ALL CUSTOMERS =====");

            for (Customer c : customerDAO.getAllCustomers()) {

                System.out.println(
                        c.getId() + " | " +
                                c.getName() + " | " +
                                c.getEmail() + " | " +
                                c.getPhone() + " | " +
                                c.getAddress()
                );
            }
            // =========================
            // UPDATE CUSTOMER
            // =========================

            Customer updatedCustomer = new Customer(
                    1,
                    "Subha Barik",
                    "subha@gmail.com",
                    "9876543210",
                    "Kolkata"
            );

            customerDAO.updateCustomer(updatedCustomer);

            System.out.println("Customer updated successfully!");


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}