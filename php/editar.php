<?php
include 'ejemplo.php';
$id=$_POST["id"];
$nombre=$_POST["nombre"];
$equipo=$_POST["equipo"];
$foto=$_POST["foto"];


$consulta="UPDATE jugador SET nombre_jugador='$nombre', equipo='$equipo', foto='$foto' WHERE id='$id'";
mysqli_query($conexion, $consulta) or die (mysqli_error());
mysqli_close($conexion);
?>