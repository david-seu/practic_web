<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="david_seu.practicweb.dao.GenericDao" %>
<%@ page import="david_seu.practicweb.model.Child" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Order</title>
    <script>
        const children = [];
        window.onload = function() {
            const form = document.querySelector('form');

            const newButton = document.createElement('button');
            newButton.textContent = 'New';
            newButton.addEventListener('click', function(e) {
                e.preventDefault();
                // Push current child to children array
                const formData = new FormData(form);
                const child = {};
                for (const [key, value]  of formData.entries()) {
                    child[key] = value;
                }
                child['user'] = sessionStorage.getItem('user');
                children.push(child);
                // Reset form fields
                form.reset();
            });
            form.appendChild(newButton);

            form.addEventListener('submit', function(e) {
                e.preventDefault();
                e.preventDefault();
                // Push current child to children array
                const formData = new FormData(form);
                const child = {};
                for (const [key, value]  of formData.entries()) {
                    child[key] = value;
                }
                children.push(child);
                //create a java chidlren that is a list of childr

                <% List<Child> children %> = children;

                <% GenericDao<Child> dao = new GenericDao<>(Child.class);

                for (Child child : children) {
                    dao.save(child);
                } %>
            });
        };
    </script>
</head>

<body>
<h1>Create Order</h1>
<form action="child" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="quantity">Quantity:</label><br>
    <input type="text" id="quantity" name="quantity"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>