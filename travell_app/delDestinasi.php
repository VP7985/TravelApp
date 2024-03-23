<?php

include "DBCon.php";

$id_des = isset($_POST['id_des']) ? $_POST['id_des'] : '';

if (empty($id_des)){
    echo "Kode Destinasi tidak boleh kosong";
}else{
    $query = mysqli_query($con, "DELETE FROM destinasi WHERE id_des = '".$id_des."'");

    if ($query){
        echo "Berhasil Menghapus Destinasi";
    }else{
        echo "Gagal Menghapus Destinasi";
    }
}

?>