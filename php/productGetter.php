<?php
if (isset($_REQUEST['searchFor'])) {
	$searchFor = $_REQUEST["searchFor"];
} else {
	$searchFor='';
}

$hint = "";

if ($searchFor !== "") {
    $searchFor = strtolower($searchFor);
    $len=strlen($searchFor);

	$db = mysqli_connect("localhost","root","");

	if($db) {
		$result = mysqli_select_db($db, "adventureworks") or die("Doslo je do problema prilikom odabira baze...");
		$sql="SELECT * FROM department where `Name` LIKE '%$searchFor%'";
		
		$result2 = mysqli_query($db, $sql) or die("Doslo je do problema prilikom izvrsavanja upita...");
		$n=mysqli_num_rows($result2);

		if ($n > 0){
			while ($myrow=mysqli_fetch_row($result2)){
					$hint .= $myrow[1] . "||";
				}
			}
	}
	else {
		echo "<br>Nije proslo spajanje na bazu<br>";
	}		
}

echo $hint === "" ? "No matches for " . $searchFor : $hint;

?>