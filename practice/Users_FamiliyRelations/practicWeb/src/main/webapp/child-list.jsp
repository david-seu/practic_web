<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Child List</title>
    <script>
        window.onload = function() {
            fetch('/practicWeb_war_exploded/child')
                .then(response => response.json())
                .then(children => {
                    const childTable = document.getElementById('child-table');
                    children.forEach(child => {
                        const childRow = document.createElement('tr');
                        const idCell = document.createElement('td');
                        idCell.textContent = child.id;
                        childRow.appendChild(idCell);
                        const nameCell = document.createElement('td');
                        nameCell.textContent = child.name;
                        childRow.appendChild(nameCell);
                        const deleteCell = document.createElement('td');
                        const deleteButton = document.createElement('button');
                        deleteButton.textContent = 'Delete';
                        deleteButton.onclick = function() {
                            fetch('/practicWeb_war_exploded/child?id=' + child.id, { method: 'DELETE' })
                                .then(response => {
                                    if (response.ok) {
                                        childRow.remove();
                                    } else {
                                        alert('Error: ' + response.statusText);
                                    }
                                });
                        };
                        deleteCell.appendChild(deleteButton);
                        childRow.appendChild(deleteCell);
                        childTable.appendChild(childRow);

                        // Add event listener to the row
                        childRow.addEventListener('dblclick', function() {
                            window.location.href = '/practicWeb_war_exploded/child-update.jsp?id=' + child.id;
                        });
                    });
                });
        };
    </script>
</head>
<body>
<h1>Child List</h1>
<a href="child-create.jsp">Create New Child</a>
<br/>
<table id="child-table">
    <thead>
    <tr>
        <th>Child ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>