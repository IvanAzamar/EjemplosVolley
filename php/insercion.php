<?php
include 'ejemplo.php';

$nombre=$_POST["nombre"];
$equipo=$_POST["equipo"];
$foto=$_POST["foto"];

$consulta="insert into jugador(nombre_jugador, equipo, foto) VALUES ('$nombre','$equipo','$foto')";
mysqli_query($conexion,$consulta) or die (mysql_error());
mysqli_close($conexion);
echo "registro insertado";
?>
