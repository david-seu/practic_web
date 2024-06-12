<?php
require_once "configuration.php";
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
$postdata = file_get_contents("php://input");
$request = json_decode($postdata);
$c_id = $request->c_id;
$name = $request->name;
$sql_query = "update `childclass` set name='$name' where c_id = $c_id;";
global $connection;
$result = mysqli_query($connection, $sql_query);
if($result){
    echo json_encode(array(
        "c_id" => $c_id,
        "name" => $name,
    ));
} else {
    echo "Error: " . $sql_query . "<br>" . mysqli_error($connection);
}
mysqli_close($connection);