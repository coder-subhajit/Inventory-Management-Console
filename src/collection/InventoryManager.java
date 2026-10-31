package collection;
import exception.OutOfStockException;
import model.Product;

import java.sql.SQLOutput;
import java.util.HashMap;

public class InventoryManager {
    //Create a HashMap
    private final HashMap<Integer,Product> products;

    public InventoryManager(){
        products=new HashMap<>();
    }

    //Add a product
    public void addProduct(Product product){
        products.put(product.getId(),product);
    }

    //Find a product
    public Product getProduct(int id){
        return products.get(id);
    }

    //Remove a product
    public void removeProduct(int id){
        products.remove(id);
    }

    //Checks whether a product exists
    public boolean containsProduct(int id){
        return products.containsKey(id);
    }

    //Get all product details
    public void displayProducts() {
        for (Product product : products.values()) {
            System.out.println(
                    product.getId() + " | " + product.getName() + " | ₹" + product.getPrice());
        }
    }
    //Add the Exception method I mean use the Exception
        public void checkStock(Product product,int quantity)
        throws OutOfStockException {
            if (quantity <= 0) {
                throw new OutOfStockException("Quantity must be greater than zero");
            }
            //Temporary stock value
            int availableStock = 5;
            if (quantity > availableStock) {
                throw new OutOfStockException("Not enough stock for:" + product.getName());
            }
            System.out.println("Stock available");
        }

        //Add getAllProducts()
    public HashMap<Integer, Product> getProducts() {
        return products;
    }
    }