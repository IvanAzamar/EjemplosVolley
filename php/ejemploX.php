<?php
$nombre=$_GET["n"];
$c=mysqli_connect("localhost","root","","ejemploX");
$query=mysqli_query($c,"INSERT INTO datos (nombre) VALUES
('$nombre')");
echo "registro exitoso";
?>