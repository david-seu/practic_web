<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Parent</title>
<%--    <script>--%>
<%--        window.onload = function() {--%>
<%--            const form = document.querySelector('form');--%>
<%--            if(sessionStorage.getItem('id') === null) {--%>
<%--                window.location.href = '/practicWeb_war_exploded/index.jsp';--%>
<%--            }--%>
<%--            form.addEventListener('submit', function(e) {--%>
<%--                e.preventDefault();--%>
<%--                const formData = new FormData(form);--%>
<%--                const jsonObject = {};--%>
<%--                for (const [key, value]  of formData.entries()) {--%>
<%--                    jsonObject[key] = value;--%>
<%--                }--%>
<%--                fetch(form.action, {--%>
<%--                    method: 'POST',--%>
<%--                    headers: {--%>
<%--                        'Content-Type': 'application/json',--%>
<%--                    },--%>
<%--                    body: JSON.stringify(jsonObject),--%>
<%--                }).then(response => {--%>
<%--                    if (!response.ok) {--%>
<%--                        alert('Error: ' + response.statusText);--%>
<%--                    } else {--%>
<%--                        window.location.href = '/practicWeb_war_exploded/parent-list.jsp'; // or any other page you want to redirect to--%>
<%--                    }--%>
<%--                });--%>
<%--            });--%>
<%--        };--%>
<%--    </script>--%>
    <script>
        window.onload = function() {
            if(sessionStorage.getItem('id') === null) {
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            }

            const form = document.querySelector('form');
            const parents = []; // Store all parents

            // New button
            const newButton = document.createElement('button');
            newButton.textContent = 'New';
            newButton.addEventListener('click', function(e) {
                e.preventDefault();
                // Push current parent to parents array
                const formData = new FormData(form);
                const parent = {};
                for (const [key, value]  of formData.entries()) {
                    parent[key] = value;
                }
                parent['userId'] = sessionStorage.getItem('id');
                parents.push(parent);
                // Reset form fields
                form.reset();
            });
            form.appendChild(newButton);

            form.addEventListener('submit', function(e) {
                e.preventDefault();
                // Push current parent to parents array
                const formData = new FormData(form);
                const parent = {};
                for (const [key, value]  of formData.entries()) {
                    parent[key] = value;
                }
                parent['userId'] = sessionStorage.getItem('id');
                parents.push(parent);
                // Send parents array
                fetch(form.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(parents),
                }).then(response => {
                    if (!response.ok) {
                        alert('Error: ' + response.statusText);
                    } else {
                        window.location.href = '/practicWeb_war_exploded/parent-list.jsp'; // or any other page you want to redirect to
                    }
                });
            });
        };
    </script>
</head>
<body>
<h1>Create Parent</h1>
<form action="parent" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <input type="submit" value="Create">
</form>
</body>
</html>