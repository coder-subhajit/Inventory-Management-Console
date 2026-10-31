package dao;

public class ProductDAOTest {

    public static void main(String[] args) {

        try {

            ProductDAO productDAO = new ProductDAO();

            productDAO.deleteProduct(3);

            System.out.println("Product deleted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}