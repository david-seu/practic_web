<?php
require_once 'configuration.php';
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
// $pageno = $_GET['pageno'];
// $no_of_records_per_page = 5;
// $offset = ($pageno-1) * $no_of_records_per_page;

// $sql_query = "SELECT * FROM `files` WHERE userid = '$_GET[id]' LIMIT $no_of_records_per_page OFFSET $offset";
$sql_query = "SELECT * FROM `parents` WHERE u_id = '$_GET[id]'";
global $connection;
$result = mysqli_query($connection, $sql_query);
if ($result) {
    $number_of_rows = mysqli_num_rows($result);
    $requested_parents = array();
    for ($i = 0; $i < $number_of_rows; $i++) {
        $row = mysqli_fetch_array($result);
        array_push($requested_parents, array(
            "name" => $row['name'],
            "u_id" => $row['u_id'],
        ));    
    }
    mysqli_free_result($result);
    echo json_encode($requested_parents);
}
mysqli_close($connection);

