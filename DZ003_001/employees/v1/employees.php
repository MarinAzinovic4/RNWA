<?php
// Connect to database
	include("../connection.php");
	include("../functions.php");
	$db = new dbObj();
	$connection =  $db->getConnstring();
 
	$request_method=$_SERVER["REQUEST_METHOD"];

	switch($request_method) {
		case 'GET':
			if(!empty($_GET["id"])) {
				$id = intval($_GET["id"]);
				get_employees($id);
			} else {
				get_employees();
			}

			break;
		case 'POST':
			insert_employee();

			break;
		case 'PUT':
			if (isset($_GET["id"])) {
				$id=intval($_GET["id"]);
				update_employee($id);				
			} else {
				header('Content-Type: application/json');
				echo json_encode("Error while calling method and parametars");
			}				
			
			break;				
		case 'DELETE':
			$id = intval($_GET["id"]);
			delete_employee($id);
			
			break;
		default:
			header("HTTP/1.0 405 Method Not Allowed");
			break;
	}
?>
