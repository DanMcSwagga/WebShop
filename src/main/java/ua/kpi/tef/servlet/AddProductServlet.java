package ua.kpi.tef.servlet;

import ua.kpi.tef.model.entity.DBProduct;
import ua.kpi.tef.model.entity.Product;
import ua.kpi.tef.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class AddProductServlet extends HttpServlet {

    private Map<Integer, Product> products;

    private static final String QUERY_INSERT_NEW = "INSERT INTO product VALUES (?, ?, ?)";

    @Override
    public void init() throws ServletException {

        final Object products = getServletContext().getAttribute("products");

        if (products == null || !(products instanceof HashMap)) {

            throw new IllegalStateException("Your repo doesn't initialize!");
        } else {

            this.products = (HashMap<Integer, Product>) products;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        if (Utils.requestIsValid(req)) {

            final int marking = Integer.valueOf(req.getParameter("marking"));
            final String title = req.getParameter("title");
            final int price = Integer.valueOf(req.getParameter("price"));

            final Product product = new Product();

            product.setMarking(marking);
            product.setTitle(title);
            product.setPrice(price);

            products.put(marking, product);


            // adding to DataBase [unnecessary]
            DBProduct dbProduct = new DBProduct();
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = dbProduct.getConnection().prepareStatement(QUERY_INSERT_NEW);

                preparedStatement.setInt(1, marking);
                preparedStatement.setString(2, title);
                preparedStatement.setInt(3, price);

                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}
