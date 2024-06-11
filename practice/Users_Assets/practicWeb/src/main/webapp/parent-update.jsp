<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Parent</title>
    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            const parentId = urlParams.get('id');
            fetch('/practicWeb_war_exploded/parent?id=' + parentId)
                .then(response => response.json())
                .then(parent => {
                    document.getElementById('id').value = parent.id;
                    document.getElementById('name').value = parent.name;
                });

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
<h1>Update Parent</h1>
<form action="parent" method="post">
    <label for="id">Parent ID:</label><br>
    <input type="number" id="id" name="id" readonly><br>
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>