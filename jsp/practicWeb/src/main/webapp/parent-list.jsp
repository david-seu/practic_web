<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Parent List</title>
    <script>
        window.onload = function() {
            if(sessionStorage.getItem('id') === null) {
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            }
            fetch('/practicWeb_war_exploded/parent?name=&type=all-by-name')
                .then(response => response.json())
                .then(parents => {
                    const parentTable = document.getElementById('parent-table');
                    parents.forEach(parent => {
                        const parentRow = document.createElement('tr');
                        const idCell = document.createElement('td');
                        idCell.textContent = parent.id;
                        parentRow.appendChild(idCell);
                        const nameCell = document.createElement('td');
                        nameCell.textContent = parent.name;
                        parentRow.appendChild(nameCell);
                        const deleteCell = document.createElement('td');
                        const deleteButton = document.createElement('button');
                        deleteButton.textContent = 'Delete';
                        deleteButton.onclick = function() {
                            fetch('/practicWeb_war_exploded/parent?id=' + parent.id, { method: 'DELETE' })
                                .then(response => {
                                    if (response.ok) {
                                        parentRow.remove();
                                    } else {
                                        alert('Error: ' + response.statusText);
                                    }
                                });
                        };
                        deleteCell.appendChild(deleteButton);
                        parentRow.appendChild(deleteCell);
                        parentTable.appendChild(parentRow);


                        parentRow.addEventListener('dblclick', function() {
                            window.location.href = '/practicWeb_war_exploded/parent-update.jsp?id=' + parent.id;
                        });
                    });
                });

            const input = document.getElementById('filter-name');
            const parentTable = document.getElementById('parent-table');

            input.addEventListener('input', function() {
                //clear table body
                console.log(input.value);
                parentTable.querySelector('tbody').innerHTML = '';
                parentTable.innerHTML = '<thead> <tr> <th>Parent ID</th> <th>Name</th> <th>Action</th> </tr> </thead> <tbody> </tbody>';
                fetch('/practicWeb_war_exploded/parent?name=' + input.value + '&type=all-by-name')
                    .then(response => response.json())
                    .then(parents => {
                        parents.forEach(parent => {
                            const parentRow = document.createElement('tr');
                            const idCell = document.createElement('td');
                            idCell.textContent = parent.id;
                            parentRow.appendChild(idCell);
                            const nameCell = document.createElement('td');
                            nameCell.textContent = parent.name;
                            parentRow.appendChild(nameCell);
                            const deleteCell = document.createElement('td');
                            const deleteButton = document.createElement('button');
                            deleteButton.textContent = 'Delete';
                            deleteButton.onclick = function() {
                                fetch('/practicWeb_war_exploded/parent?id=' + parent.id, { method: 'DELETE' })
                                    .then(response => {
                                        if (response.ok) {
                                            parentRow.remove();
                                        } else {
                                            alert('Error: ' + response.statusText);
                                        }
                                    });
                            };
                            deleteCell.appendChild(deleteButton);
                            parentRow.appendChild(deleteCell);
                            parentTable.appendChild(parentRow);


                            parentRow.addEventListener('dblclick', function() {
                                window.location.href = '/practicWeb_war_exploded/parent-update.jsp?id=' + parent.id;
                            });
                        });
                    });
            });

            const logoutButton = document.getElementById('logout-button');
            logoutButton.addEventListener('click', function() {
                sessionStorage.clear();
                window.location.href = '/practicWeb_war_exploded/index.jsp';
            });
        };
    </script>
</head>
<body>
<h1>Parent List</h1>
<button id="logout-button">Logout</button>
<a href="parent-create.jsp">Create New Parent</a>
<br/>
<label for="filter-name">Filter by name:</label><br>
<input type="text" id="filter-name" name="filter-name" value=""><br>
<br />
<table id="parent-table">
    <thead>
    <tr>
        <th>Parent ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>
</body>
</html>