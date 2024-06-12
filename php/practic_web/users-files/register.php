<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$username = $request->username;
$password = $request->password;

$sql_query = "insert into `users` (username, password) values ('$username', '$password')";
global $connection;
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo "Your user was added successfully!";
    echo $result;
} else {
    echo "Oops!Something went wrong and your user cannot be added!Please try again later.";
    echo die(mysqli_error($connection));
}
mysqli_close($connection);