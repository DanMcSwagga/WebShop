package ua.kpi.tef.servlet;

import ua.kpi.tef.model.entity.DBProduct;
import ua.kpi.tef.model.entity.Product;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener {

    private Map<Integer, Product> products;

    private static final String QUERY_SELECT_ALL = "SELECT * FROM product";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        final ServletContext servletContext =
                servletContextEvent.getServletContext();

        products = new HashMap<>();

        servletContext.setAttribute("products", products);


        DBProduct dbProduct = new DBProduct();
        try {
            Statement statement = dbProduct.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_SELECT_ALL);

            while (resultSet.next()) {
                final Product product = new Product(resultSet.getInt("marking"),
                        resultSet.getString("title"),
                        resultSet.getInt("price"));

                this.products.put(product.getMarking(), product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close recourse.
        products = null;
    }
}