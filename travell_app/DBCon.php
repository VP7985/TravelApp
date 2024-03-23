<?php

$server = "localhost";
$user = "root";
$pass = "";
$database = "travell_app";

$con = mysqli_connect($server, $user, $pass, $database);


if (mysqli_connect_errno()){
    die("Database Connection Failed : " . $con->connect_error);
}

?>