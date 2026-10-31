package service;

//import collection.InventoryManager;
import model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import dao.ProductDAO;

public class ProductService {
    private ProductDAO productDAO;//dependency injection

    public ProductService(ProductDAO productDAO){
        this.productDAO=productDAO;
    }

    //Add addProduct() to create
    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
        System.out.println("Product added successfully: " + product.getName());
    }

    //Add getProduct() to read one
    public Product getProduct(int id) throws SQLException {
        return productDAO.getProduct(id);
    }

    //To remove product to delete
    public void removeProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);//I have to improve this
        System.out.println("Product removed successfully.");
    }

    //Add getAllProducts() to read all
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    //Sort by price by ascending
    public List<Product> sortByPriceAscending() throws SQLException {
        List<Product> products = getAllProducts();
        products.sort(Comparator.comparing(Product::getPrice));
        return products;
    }


    //Sort by price descending
    public List<Product> sortByPriceDescending() throws SQLException {
        List<Product> products = getAllProducts();
        products.sort(Comparator.comparing(Product::getPrice).reversed());
        return products;
    }

    //Sort by name
    public List<Product> sortByName() throws SQLException {
        List<Product> products = getAllProducts();
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    //Display all products
    public void displayProducts() throws SQLException {
        List<Product> products = productDAO.getAllProducts();

        for (Product product : products) {
            System.out.println(
                    product.getId() + " | " +
                            product.getName() + " | " +
                            product.getCategory() + " | ₹" +
                            product.getPrice()
            );
        }
    }

    //Display the sorted results
    public void displayProductList(List<Product> products) {

        for (Product product : products) {

            System.out.println(
                    product.getId() + " | " +
                            product.getName() + " | " +
                            product.getCategory() + " | ₹" +
                            product.getPrice()
            );
        }
    }

    //Update product
    public void updateProduct(Product product) throws SQLException {

        productDAO.updateProduct(product);

        System.out.println(
                "Product updated successfully: " + product.getName()
        );
    }
}
