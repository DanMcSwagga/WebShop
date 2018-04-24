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
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProductServlet extends HttpServlet {

    private Map<Integer, Product> products;

    private static final String QUERY_DELETE_OLD = "DELETE FROM product WHERE marking = ?;";

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

        if (Utils.idIsNumber(req)) {
            final int marking = Integer.valueOf(req.getParameter("marking"));
            products.remove(marking);

            // removing from DataBase [unnecessary]
            DBProduct dbProduct = new DBProduct();
            try {
                PreparedStatement preparedStatement = dbProduct.getConnection().prepareStatement(QUERY_DELETE_OLD);

                preparedStatement.setInt(1, marking);

                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }
}