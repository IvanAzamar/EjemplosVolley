<?php
include 'ejemplo.php';
$id=$_POST["id"];

$consulta="DELETE FROM jugador WHERE id='$id'";
mysqli_query($conexion, $consulta) or die (mysqli_error());
mysqli_close($conexion);
echo "Registro eliminado";
?>