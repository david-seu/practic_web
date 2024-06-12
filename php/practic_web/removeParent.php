<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";
global $connection;
$p_id = $_GET['p_id'];
$sql_query = "delete from parentclass where p_id = '$p_id';";
$result = mysqli_query($connection, $sql_query);
if ($result) {
    echo json_encode(array(
        "p_id" => $p_id,
    ));
}
else {
    echo "Error: " . $sql_query . "<br>" . mysqli_error($connection);
}
mysqli_close($connection);
