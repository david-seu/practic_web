<?php
require_once 'configuration.php';
header("Access-Control-Allow-Origin: *");
$sql_query = "select * from `childclass`";
global $connection;
$result = mysqli_query($connection, $sql_query);

if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_childs = array();
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
        array_push($requested_childs, array(
            "c_id" => $row['c_id'], 
            "name" => $row['name'],
            "p_id" => $row['p_id'],
        ));    
    }
    mysqli_free_result($result);
    echo json_encode($requested_childs);
}
mysqli_close($connection);