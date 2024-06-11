<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Child List</title>
    <script>
        window.onload = function() {
            if(sessionStorage.getItem('id') === null) {
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            }
            fetch('/practicWeb_war_exploded/child?id=' + sessionStorage.getItem('id'))
                .then(response => response.json())
                .then(children => {
                    console.log(children);
                    const childTable = document.getElementById('child-table');
                    children.forEach(child => {
                        const childRow = document.createElement('tr');
                        const nameCell = document.createElement('td');
                        nameCell.textContent = child.name;
                        childRow.appendChild(nameCell);
                        const descriptionCell = document.createElement('td');
                        descriptionCell.textContent = child.description;
                        childRow.appendChild(descriptionCell);
                        const valueCell = document.createElement('td');
                        valueCell.textContent = child.value;
                        if(child.value > 10)
                        {
                            valueCell.style.color = 'red';
                        }
                        childRow.appendChild(valueCell);
                        childTable.appendChild(childRow);
                    });
                });

            const logoutButton = document.querySelector('button');
            logoutButton.addEventListener('click', function() {
                sessionStorage.removeItem('id'); // Remove id from session storage
                window.location.href = '/practicWeb_war_exploded/login.jsp'; // Redirect to login page
            });
        };
    </script>
</head>
<body>
<h1>Assets List</h1>
<a href="child-create.jsp">Create New Assets</a>
<br/>
<button>Logout</button>
<table id="child-table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Value</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>