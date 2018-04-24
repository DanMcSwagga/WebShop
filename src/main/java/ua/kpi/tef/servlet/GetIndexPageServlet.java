package ua.kpi.tef.servlet;

import ua.kpi.tef.model.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetIndexPageServlet extends HttpServlet {

    private Map<Integer, Product> products;

    @Override
    public void init() throws ServletException {

        final Object products = getServletContext().getAttribute("products");

        if (products == null || !(products instanceof HashMap)) {

            throw new IllegalStateException("Your repo doesn't initialize!");
        } else {

            this.products = (HashMap<Integer, Product>) products;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("products", products.values());
        req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
    }
}

