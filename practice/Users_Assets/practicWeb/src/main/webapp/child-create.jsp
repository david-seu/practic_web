<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Child</title>
    <script>
        window.onload = function() {
            if(sessionStorage.getItem('id') === null) {
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            }

            const form = document.querySelector('form');
            const children = []; // Store all children

            // New button
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
                child['parentId'] = sessionStorage.getItem('id');
                children.push(child);
                // Reset form fields
                form.reset();
            });
            form.appendChild(newButton);

            form.addEventListener('submit', function(e) {
                e.preventDefault();
                // Push current child to children array
                const formData = new FormData(form);
                const child = {};
                for (const [key, value]  of formData.entries()) {
                    child[key] = value;
                }
                child['parentId'] = sessionStorage.getItem('id');
                children.push(child);
                // Send children array
                fetch(form.action, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(children),
                }).then(response => {
                    if (!response.ok) {
                        alert('Error: ' + response.statusText);
                    } else {
                        window.location.href = '/practicWeb_war_exploded/child-list.jsp'; // or any other page you want to redirect to
                    }
                });
            });
        };
    </script>
</head>

<body>
<h1>Create Asset</h1>
<form action="child" method="post">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="description">Description:</label><br>
    <input type="text" id="description" name="description"><br>
    <label for="value">Value:</label><br>
    <input type="text" id="value" name="value"><br>
    <input type="submit" value="Create">
</form>
</body>
</html>