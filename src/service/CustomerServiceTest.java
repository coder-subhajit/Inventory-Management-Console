package service;

import dao.CustomerDAO;

public class CustomerServiceTest {

    public static void main(String[] args) {

        try {

            CustomerDAO customerDAO = new CustomerDAO();

            CustomerService customerService =
                    new CustomerService(customerDAO);

            System.out.println("===== ALL CUSTOMERS =====");

            customerService.displayCustomers();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}