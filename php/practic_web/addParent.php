<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$name = $request->name;
$u_id = $request->u_id;

$sql_query = "insert into `parentclass` (name, u_id) values ('$name', '$u_id')";
global $connection;
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo json_encode(array(
        "id" => $connection->insert_id,
        "name" => $name,
        "u_id" => $u_id,
    ));
} else {
    echo "Error: " . $sql_query . "<br>" . mysqli_error($connection);
}
mysqli_close($connection);