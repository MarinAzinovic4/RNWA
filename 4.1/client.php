<?php

header('Content-Type: text/plain');
try{
	ini_set('soap.wsdl_cache_enabled',0);
	ini_set('soap.wsdl_cache_ttl',0);
	
	$endPointWSDL = "http://localhost/4.1/converter.wsdl";
  
	$sClient = new SoapClient($endPointWSDL,
							array(
							'cache_wsdl'=>WSDL_CACHE_NONE,
							'trace' => 1)
							);

		echo "Odgovor web servisa na doConvertBamToEur(12): ";
		$response = $sClient->doConvertBamToEur(12);
		var_dump($response);
		
		echo "Odgovor web servisa na doConvertEurToBam(12): ";
		$response = $sClient->doConvertEurToBam(12);
		var_dump($response);
		
		echo "Odgovor web servisa na doConvertBamToHrk(12): ";
		$response = $sClient->doConvertBamToHrk(12);
		var_dump($response);
		
		echo "Odgovor web servisa na doConvertHrkToBam(12): ";
		$response = $sClient->doConvertHrkToBam(12);
		var_dump($response);


} catch(SoapFault $e){
  var_dump($e);
  echo $e;
}

?>