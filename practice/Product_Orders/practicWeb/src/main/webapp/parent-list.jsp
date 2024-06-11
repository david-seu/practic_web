<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="david_seu.practicweb.model.Parent" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parent List</title>
</head>
<body>
<h1>Parent List</h1>
<a href="parent-create.jsp">Create New Product</a>
<a href="child-create.jsp">Create New Order</a>
<br/>
<table id="parent-table">
    <thead>
    <tr>
        <td>Name</td>
        <td>Description</td>
    </tr>
    </thead>
    <tbody>
        <% List<Parent> parents = (List<Parent>) request.getAttribute("parents");
            for (Parent parent : parents) { %>
        <tr>
            <td><%= parent.getName() %></td>
            <td><%= parent.getDescription() %></td>
        </tr>
        <% } %>
    </tbody>
</table>
</body>
</html>