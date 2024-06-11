<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Home</title>
  <script>
    window.onload = function() {
      const form = document.querySelector('form');
      form.addEventListener('submit', function(e) {
        e.preventDefault();
        const username = document.querySelector('#username').value;
        sessionStorage.setItem('username', username);
        window.location.href = 'parent-list.jsp';
        });
    };
  </script>
</head>
<body>
<h1>Username</h1>
<br/>
<form>
<label for="username"></label>
<input type="text" id="username" name="username" />
  <input type="submit" value="Login" />
</form>
</body>
</html>