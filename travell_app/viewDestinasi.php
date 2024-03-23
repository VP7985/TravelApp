<?php 

include "DBCon.php";

$id_des = isset($_POST['id_des']) ? $_POST['id_des'] : '';

class emp{};

if (empty($id_des)){
    echo "Data Destinasi tidak ada";
}else{
    $query = mysqli_query($con, "SELECT * FROM destinasi WHERE id_des = '$id_des'");
    $row = mysqli_fetch_array($query);

    if (!empty($row)){
        $response = new emp();
        $response->id_des = $row["id_des"];
        $response->nm_des = $row["nm_des"];
        $response->lok_des = $row["lok_des"];
        $response->hrg_des = $row["hrg_des"];
        $response->desc_des = $row["desc_des"];
        $response->img_des = $row["img_des"];

        echo (json_encode($response));
    }else{
        echo "Gagal Mengupdate Destinasi";
    }
}

?>