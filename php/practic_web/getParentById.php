<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
global $connection;
$p_id = $_GET['p_id'];
$sql_query = "select * from parentclass where p_id = '$p_id';";
$result = mysqli_query($connection, $sql_query);
$parent = mysqli_fetch_array($result);
echo json_encode(array(
    "p_id" => $parent['p_id'],
    "name" => $parent['name'],
    
));
mysqli_close($connection);