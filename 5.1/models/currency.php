<?php
    class Currency  {
        private $CurrencyCode;
        private $Name;
        private $ModifiedDate;


        public function __construct($CurrencyCode = null, $Name, $ModifiedDate) {
            $this->Name = $Name;
            $this->ModifiedDate = $ModifiedDate;
        }


        public function getCurrencyCode() {
            return $this->CurrencyCode;
        }

        public function getName() {
            return $this->Name;
        }
        public function setName($name) {
            return $this->Name = $name;
        }
        
        public function getModifiedDate() {
            return $this->ModifiedDate;
        }
        public function setModifiedDate($modifiedDate) {
            return $this->ModifiedDate = $modifiedDate;
        }


        public static function getAllCurrencys() {
            $Currencys = [];
            $db = Db::getInstance();
            $request = $db->query('SELECT * FROM currency');
                foreach($request->fetchAll() as $cult) {
                $list[] = new Currency($cult['CurrencyCode'], $cult['Name'], $cult['ModifiedDate']);
            }

            return $Currencys;
          }


          public static function findById($id) {
            $db = Db::getInstance();

            $request = $db->prepare('SELECT * FROM currency WHERE CurrencyCode = :id');
            $request->execute(array('id' => $id));
            $Currency = $request->fetch();
            
            if ($Currency) {
                return new Currency($Currency['CurrencyCode'], $Currency['Name'], $Currency['GroupName'], $Currency['ModifiedDate']);
            } else {
                return [];
            }
          }


          public function editCurrencyById($id, $newName, $newModifiedDate){
            $db = Db::getInstance();

            $sql = "UPDATE Currency SET 'Name' = '$newName', 'ModifiedDate' = '$newModifiedDate') VALUES WHERE CurrencyCode = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Currency updated!";
            } else {
                $rez="Currency not updated!";;
            }
            
            return $rez; 
          }


          public function addCurrency($CurrencyCode, $Name, $ModifiedDate){
            $db = Db::getInstance();

            $sql = "INSERT INTO currency (`CurrencyCode`, `Name`, `ModifiedDate`) VALUES ('$CurrencyCode', '$Name', '$ModifiedDate')";

            if ($db->query($sql) == TRUE){
                $rez="Currency added!";
            } else {
                $rez="Currency not added!";;
            }
            
            return $rez; 
          }


          public static function deleteCurrencyById($id) {
            $db = Db::getInstance();
            $sql = "DELETE FROM currency WHERE CurrencyCode = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Culture deleted";
            } else {
                $rez="Deletion error!";;
            }
            
            return $rez; 
        }
    }
?>