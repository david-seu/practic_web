<?php
require_once 'configuration.php';
header("Access-Control-Allow-Origin: *");
$sql_query = "select * from `parentclass`";
global $connection;
$result = mysqli_query($connection, $sql_query);

if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_parents = array();
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
        array_push($requested_parents, array(
            "p_id" => $row['p_id'], 
            "name" => $row['name']
        ));    
    }
    mysqli_free_result($result);
    echo json_encode($requested_parents);
}
mysqli_close($connection);