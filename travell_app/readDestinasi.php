<?php

include "DBCon.php";

$query = mysqli_query($con, "SELECT * FROM destinasi");

while ($r = mysqli_fetch_assoc($query))
    $output[] = $r;
    print(json_encode($output));
    print("\n");
    mysqli_close($con);
?>