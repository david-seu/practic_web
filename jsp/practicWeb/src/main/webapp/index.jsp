<%--@elvariable id="password" type="java.lang.String"--%>
<%--@elvariable id="username" type="java.lang.String"--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Users Assets</title>
  <script>
    window.onload = function() {
      const form = document.querySelector('form');
      form.addEventListener('submit', function(e) {
        e.preventDefault();
        const formData = new FormData(form);
        const username = formData.get('username');
        const password = formData.get('password');



        fetch('/practicWeb_war_exploded/user?username=' + username + '&password=' + password, {
          method: 'GET',
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
<h1>Login</h1>
<a href="/practicWeb_war_exploded/user-create.jsp">Register</a>
<form action="parent" method="get">
  <label for="username">Username:</label><br>
  <input type="text" id="username" name="username"><br>
  <label for="password">Password:</label><br>
  <input type="password" id="password" name="password"><br>
  <input type="submit" value="Login">
</form>
</body>
</html>