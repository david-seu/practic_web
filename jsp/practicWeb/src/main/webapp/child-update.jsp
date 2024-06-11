<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Child</title>
    <script>
        window.onload = function() {
            if(sessionStorage.getItem('id') === null) {
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            }
            const urlParams = new URLSearchParams(window.location.search);
            const childId = urlParams.get('id');
            fetch('/practicWeb_war_exploded/child?type=one&id=' + childId)
                .then(response => response.json())
                .then(child => {
                    document.getElementById('id').value = child.id;
                    document.getElementById('parentId').value = child.parentId;
                    document.getElementById('name').value = child.name;
                });

            const form = document.querySelector('form');
            form.addEventListener('submit', function(e) {
                e.preventDefault();
                const formData = new FormData(form);
                const jsonObject = {};
                for (const [key, value]  of formData.entries()) {
                    jsonObject[key] = value;
                }
                fetch(form.action + '?type=one', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(jsonObject),
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
<h1>Update Child</h1>
<form action="child" method="post">
    <label for="id">Child ID:</label><br>
    <input type="number" id="id" name="id" readonly><br>
    <label for="parentId">Parent ID:</label><br>
    <input type="number" id="parentId" name="parentId" readonly><br>
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>