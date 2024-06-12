<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";

// Get the name from the request parameters
$name = $_GET['name'];

// Prepare the SQL statement
$stmt = $connection->prepare("SELECT * FROM parentclass WHERE name = ?");
$stmt->bind_param("s", $name);

// Execute the statement
$stmt->execute();

// Get the result
$result = $stmt->get_result();

// Fetch all the rows as an associative array
$parents = $result->fetch_all(MYSQLI_ASSOC);

// Close the statement and the connection
$stmt->close();
$connection->close();

// Output the parents as JSON
header('Content-Type: application/json');
echo json_encode($parents);