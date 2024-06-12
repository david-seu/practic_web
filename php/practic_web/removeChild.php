<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
global $connection;
$c_id = $_GET['c_id'];
$sql_query = "delete from childclass where c_id = '$c_id';";
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo json_encode(array(
        "c_id" => $c_id,
    ));
} else {
    echo "Error: " . $sql_query . "<br>" . mysqli_error($connection);
}
mysqli_close($connection);
