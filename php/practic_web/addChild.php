<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$name = $request->name;
$p_id = $request->p_id;

$sql_query = "insert into `childclass` (name, p_id) values ('$name', '$p_id')";
global $connection;
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo json_encode(array(
        "c_id" => $connection->insert_id,
        "name" => $name,
        "p_id" => $p_id,
    ));
} else {
    echo "Error: " . $sql_query . "<br>" . mysqli_error($connection);
}
mysqli_close($connection);