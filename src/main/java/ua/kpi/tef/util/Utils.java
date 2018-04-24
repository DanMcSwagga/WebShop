package ua.kpi.tef.util;

import ua.kpi.tef.model.entity.Product;

import javax.servlet.http.HttpServletRequest;

public class Utils {

    public static boolean idIsNumber(HttpServletRequest request) {
        final String marking = request.getParameter("marking");
        return marking != null &&
                (marking.length() > 0) &&
                marking.matches("[+]?\\d+");
    }

    public static Product createSingleProduct(final int marking,
                                              final String title,
                                              final int price) {
        Product product = new Product();
        product.setMarking(marking);
        product.setTitle(title);
        product.setPrice(price);
        return product;
    }

    public static boolean requestIsValid(HttpServletRequest request) {
        final String title = request.getParameter("title");
        final String price = request.getParameter("price");

        return title != null && title.length() > 0 &&
                price != null && price.length() > 0 &&
                price.matches("[+]?\\d+");
    }

}
