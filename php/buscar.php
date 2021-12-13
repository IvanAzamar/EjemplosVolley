<?php
include 'ejemplo.php';
$id=$_GET["id"];

$consulta="SELECT * FROM jugador WHERE id='$id'";
$resultado=$conexion->query($consulta);

while($fila=$resultado->fetch_array()){
    $jugadores[]= array_map('utf8_encode',$fila);
}

echo json_encode($jugadores);
$resultado->close();
?>