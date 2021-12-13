<?php
require 'ejemplo.php';
$consulta="SELECT * FROM jugador";
$registros=mysqli_query($conexion, $consulta);
if(!$registros){
    echo "fallo la consulta";
    return ;
}
echo "<table border=2>";
echo "<tr bgcolor=#999999><td>ID</td><td>NOMBRE</td><td>EQUIPO</td><td>FOTO</td></tr>";
while($r=$registros->fetch_array()){
echo "<tr><td>$r[0]</td><td>$r[1]</td>";
echo "<td>$r[2]</td><td>$r[3]</td></tr>";
}
echo "</table>";
mysqli_close($conexion);
?>