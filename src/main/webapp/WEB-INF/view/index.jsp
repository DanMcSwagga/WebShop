<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Shop</title>

</head>
<body>

<h2>Products</h2><br />

<c:forEach var="product" items="${requestScope.products}">
    <ul>

        <li>Marking: <c:out value="${product.marking}"/></li>

        <li>Title: <c:out value="${product.title}"/></li>

        <li>Price: <c:out value="${product.price}"/></li>

        <form method="post" action="/delete">
            <input type="number" hidden name="marking" value="${product.marking}" />
            <input type="submit" name="delete" value="Delete"/>
        </form>

    </ul>
    <hr />

</c:forEach>

<h2>Add new product</h2><br />

<form method="post" action="/add">

    <label><input type="number" name="marking"></label>Marking<br>
    <label><input type="text" name="title"></label>Title<br>
    <label><input type="number" name="price"></label>Price<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

</body>
</html>