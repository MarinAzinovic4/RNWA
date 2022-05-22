<?php

if(!extension_loaded("soap")){
  dl("php_soap.dll");
}
ini_set("soap.wsdl_cache_enabled","0");
$server = new SoapServer("http://localhost/4.1/converter.wsdl");



function doConvertBamToEur($value){
	return "BAM to EUR: " . $value * 0.511292;
}
function doConvertEurToBam($value){
	return "EUR to BAM: " . $value * 1.95;
}
function doConvertBamToHrk($value){
	return "BAM to HRK: " . $value * 3.86;
}
function doConvertHrkToBam($value){
	return "HRK to BAM: " . $value * 0.26;
}

$server->AddFunction("doConvertBamToEur");
$server->AddFunction("doConvertEurToBam");
$server->AddFunction("doConvertBamToHrk");
$server->AddFunction("doConvertHrkToBam");

$server->handle();
?>