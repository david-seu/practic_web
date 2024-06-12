<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
global $connection;
$c_id = $_GET['c_id'];
$sql_query = "select * from childclass where c_id = '$c_id';";
$result = mysqli_query($connection, $sql_query);
$child = mysqli_fetch_array($result);
echo json_encode(array(
    "c_id" => $child['c_id'],
    "name" => $child['name'],
    'p_id' => $child['p_id'],
    
));
mysqli_close($connection);