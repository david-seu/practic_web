<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";

// Get the raw POST data
$data = file_get_contents("php://input");

// Decode the JSON data into a PHP array
$parents = json_decode($data, true);

// Loop through the array and insert each parent into the database
foreach ($parents as $parent) {
    $name = $parent['name'];
    $u_id = $parent['u_id'];
    // Prepare the SQL statement
    $stmt = $connection->prepare("INSERT INTO parentclass (name, u_id) VALUES (?, ?)");
    // Bind the parameters
    $stmt->bind_param("si", $name, $u_id);
    // Execute the statement
    $stmt->execute();
}

mysqli_close($connection);
?>