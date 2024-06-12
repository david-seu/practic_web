<?php
header("Access-Control-Allow-Headers: *");
header("Access-Control-Allow-Origin: *");
require_once "configuration.php";

// Get the p_id from the request parameters
$p_id = $_GET['p_id'];

// Prepare the SQL statement
$stmt = $connection->prepare("SELECT * FROM children WHERE p_id = ?");
$stmt->bind_param("i", $p_id);

// Execute the statement
$stmt->execute();

// Get the result
$result = $stmt->get_result();

// Fetch all the rows as an associative array
$children = $result->fetch_all(MYSQLI_ASSOC);

// Close the statement and the connection
$stmt->close();
$connection->close();

// Output the children as JSON
header('Content-Type: application/json');
echo json_encode($children);