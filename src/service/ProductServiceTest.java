package service;

import dao.ProductDAO;
import model.Product;

public class ProductServiceTest {

    public static void main(String[] args) {

        try {

            // Create DAO
            ProductDAO productDAO = new ProductDAO();

            // Create Service
            ProductService productService =
                    new ProductService(productDAO);


            // =====================================
            // 1. DISPLAY ALL PRODUCTS
            // =====================================

            System.out.println("===== ALL PRODUCTS =====");

            productService.displayProducts();


            // =====================================
            // 2. GET ONE PRODUCT
            // =====================================

            System.out.println("\n===== ONE PRODUCT =====");

            Product product = productService.getProduct(1);

            if (product != null) {

                System.out.println(
                        product.getId() + " | " +
                                product.getName() + " | " +
                                product.getCategory() + " | ₹" +
                                product.getPrice()
                );

            } else {

                System.out.println("Product not found.");
            }


            // =====================================
            // 3. SORT BY PRICE LOW → HIGH
            // =====================================

            System.out.println("\n===== PRICE LOW TO HIGH =====");

            productService.displayProductList(
                    productService.sortByPriceAscending()
            );


            // =====================================
            // 4. SORT BY PRICE HIGH → LOW
            // =====================================

            System.out.println("\n===== PRICE HIGH TO LOW =====");

            productService.displayProductList(
                    productService.sortByPriceDescending()
            );


            // =====================================
            // 5. SORT BY NAME
            // =====================================

            System.out.println("\n===== SORT BY NAME =====");

            productService.displayProductList(
                    productService.sortByName()
            );


            // =====================================
            // 6. ADD PRODUCT
            // =====================================

            System.out.println("\n===== ADD PRODUCT =====");

            Product newProduct = new Product(
                    0,
                    "Webcam",
                    "Electronics",
                    3500
            );

            productService.addProduct(newProduct);


            // =====================================
            // 7. UPDATE PRODUCT
            // =====================================

            System.out.println("\n===== UPDATE PRODUCT =====");

            Product updatedProduct = new Product(
                    2,
                    "Wireless Mouse",
                    "Electronics",
                    1200
            );

            productService.updateProduct(updatedProduct);


            // =====================================
            // 8. DELETE PRODUCT
            // =====================================

            System.out.println("\n===== DELETE PRODUCT =====");

            // Change 6 to the ID of a product
            // you actually want to delete.
            productService.removeProduct(1);


            // =====================================
            // 9. DISPLAY FINAL PRODUCTS
            // =====================================

            System.out.println("\n===== FINAL PRODUCTS =====");

            productService.displayProducts();


        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}