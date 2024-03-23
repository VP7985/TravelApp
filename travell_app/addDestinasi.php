<?php

include "DBCon.php";

$nm_des = isset($_POST['nm_des']) ? $_POST['nm_des'] : '';
$lok_des = isset($_POST['lok_des']) ? $_POST['lok_des'] : '';
$hrg_des = isset($_POST['hrg_des']) ? $_POST['hrg_des'] : '';
$desc_des = isset($_POST['desc_des']) ? $_POST['desc_des'] : '';
$img_des = isset($_POST['img_des']) ? $_POST['img_des'] : '';

$con = mysqli_connect($server, $user, $pass, $database);


if (empty($nm_des) || empty($lok_des) || empty($hrg_des) || empty($desc_des) || empty($desc_des)){
    echo "Data tidak boleh kosong";
}else{
    $query = mysqli_query($con, "INSERT INTO destinasi (id_des, nm_des, lok_des, hrg_des, desc_des, img_des) VALUES (0, '$nm_des', '$lok_des', '$hrg_des', '$desc_des', '$img_des')");
    
    if ($query){
        echo "Berhasil Menambahkan Barang";
    }else{
        echo "Gagal Menambahkan Barang";
    }

}


?>