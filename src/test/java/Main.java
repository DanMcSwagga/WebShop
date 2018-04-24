import ua.kpi.tef.model.entity.DBProduct;
import ua.kpi.tef.model.entity.Product;

import java.sql.*;

public class Main {

    public static void main(String[] args) {

        DBProduct dbProduct = new DBProduct();

        String query = "SELECT * FROM product";
        try {
            Statement statement = dbProduct.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Product product = new Product();
                product.setMarking(resultSet.getInt("marking"));
                product.setTitle(resultSet.getString("title"));
                product.setPrice(resultSet.getInt("price"));

                System.out.println(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


//        try {
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}


//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement()) {
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM product");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }