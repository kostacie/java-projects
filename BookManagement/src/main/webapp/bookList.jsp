<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link rel="stylesheet" href="css/bookList.css">
    <title>Book Management</title>
</head>
<body>
<div class="page">
    <h1 id="header">Book Management</h1>
    <h2><a id="add-book" href="book?action=new">Add a new book</a></h2>
    <table id="book_table">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Pages</th>
            <th>Price</th>
            <th>Action</th>
        </tr>
        <c:forEach var="book" items="${listBook}">
            <tr>
                <td><c:out value="${book.id}"/></td>
                <td><c:out value="${book.name}"/></td>
                <td><c:out value="${book.author}"/></td>
                <td><c:out value="${book.pages}"/></td>
                <td><c:out value="${book.price}"/></td>
                <td>
                   <a href="book?action=edit&id=<c:out value='${book.id}'/>"><input type="button" value="Edit"></a>
                    <a href="book?action=delete&id=<c:out value='${book.id}'/>"><input type="button" value="Delete"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
