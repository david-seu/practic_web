<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";

// Get the raw POST data
$data = file_get_contents("php://input");

// Decode the JSON data into a PHP array
$children = json_decode($data, true);

// Loop through the array and insert each child into the database
foreach ($children as $child) {
    $name = $child['name'];
    $p_id = $child['p_id'];
    // Prepare the SQL statement
    $stmt = $connection->prepare("INSERT INTO childclass (name, p_id) VALUES (?, ?)");
    // Bind the parameters
    $stmt->bind_param("si", $name, $p_id);
    // Execute the statement
    $stmt->execute();
}

// Close the connection
$connection->close();
?>