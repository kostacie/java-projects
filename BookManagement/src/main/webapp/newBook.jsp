<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/newBook.css">
    <title>New Book</title>
</head>
<body>
<div>
    <h2><a href="book">List All Books</a></h2>
    <div id="input-form">
    <c:if test="${book != null}">
    <form action="book?action=update" method="post">
        </c:if>
        <c:if test="${book == null}">
        <form action="book?action=insert" method="post">
            </c:if>
            <c:if test="${book != null}">
                <input type="hidden" name="id" value="<c:out value='${book.id}'/>">
            </c:if>
            <%--        <form action="book?action=update" method="post">--%>
            <div>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="<c:out value='${book.name}'/>" placeholder="Book name"
                       required>

                <label for="author">Author:</label>
                <input type="text" id="author" name="author" value="<c:out value='${book.author}'/>"
                       placeholder="Author name" required>

                <label for="page">Pages:</label>
                <input type="number" id="page" name="pages" value="<c:out value='${book.pages}'/>"
                       placeholder="Pages amount" required>

                <label for="price">Price:</label>
                <input type="text" id="price" name="price" value="<c:out value='${book.price}'/>" placeholder="Price"
                       required>
            </div>
            <input type="submit" id="submit" value="Submit">
        </form>
    </div>
</div>
</body>
</html>
