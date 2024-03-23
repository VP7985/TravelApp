<?php 

include "DBCon.php";

$id_des = isset($_POST['id_des']) ? $_POST['id_des'] : '';
$nm_des = isset($_POST['nm_des']) ? $_POST['nm_des'] : '';
$lok_des = isset($_POST['lok_des']) ? $_POST['lok_des'] : '';
$hrg_des = isset($_POST['hrg_des']) ? $_POST['hrg_des'] : '';
$desc_des = isset($_POST['desc_des']) ? $_POST['desc_des'] : '';
$img_des = isset($_POST['img_des']) ? $_POST['img_des'] : '';

if (empty($nm_des) || empty($lok_des) || empty($hrg_des) || empty($desc_des) || empty($img_des)){
    echo "Data tidak boleh kosong";
}else if (empty($id_des)){
    $query = mysqli_query($con, "INSERT INTO destinasi (nm_des, lok_des, hrg_des, desc_des, img_des) VALUES('".$nm_des."','".$lok_des."','".$hrg_des."','".$desc_des."','".$img_des."')");

    if ($query){
        echo "Berhasil Menambahkan Destinasi";
    }else{
        echo "Gagal Menambahkan Destinasi";
    }
}else{
    $query = mysqli_query($con, "UPDATE destinasi SET nm_des = '".$nm_des."', lok_des = '".$lok_des."', hrg_des = '".$hrg_des."', desc_des = '".$desc_des."', img_des = '".$img_des."' WHERE id_des = '".$id_des."'");

    if ($query){
        echo "Berhasil Mengupdate Destinasi";
    }else{
        echo "Gagal Mengupdate Destinasi";
    }   
}

?>