<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
    <script>
        window.onload = function() {
            const form = document.querySelector('form');
            form.addEventListener('submit', function(e) {
                e.preventDefault();
                const formData = new FormData(form);
                const jsonObject = {};
                for (const [key, value]  of formData.entries()) {
                    jsonObject[key] = value;
                }
                fetch(form.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(jsonObject),
                }).then(response => {
                    if (response.ok) {
                        response.json().then(data => {
                            sessionStorage.setItem('id', data.id);
                        });
                        window.location.href = '/practicWeb_war_exploded/child-list.jsp';
                    } else {
                        response.json().then(data => {
                            alert(data.message);
                        });
                    }
                });
            });
        };
    </script>
</head>
<body>
<h1>Create User</h1>
<form action="user" method="post">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password"><br>
    <input type="submit" value="Create">
</form>
</body>
</html>