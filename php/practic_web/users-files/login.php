<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
global $connection;
$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$username = $request->username;
$password = $request->password;
$sql_query = "select * from users where username = '$username' and password = '$password';";
$result = mysqli_query($connection, $sql_query);
$user = mysqli_fetch_array($result);
echo json_encode(array(
    "id" => $user['id'],
    "username" => $user['username'],
    'password' => $user['password'],
));
mysqli_close($connection);