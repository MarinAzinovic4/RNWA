<?php
    class Culture  {
        private $CultureID;
        private $Name;
        private $ModifiedDate;


        public function __construct($CultureID = null, $Name, $ModifiedDate) {
            $this->Name = $Name;
            $this->ModifiedDate = $ModifiedDate;
        }


        public function getCultureID() {
            return $this->CultureID;
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


        public static function getAllCultures() {
            $cultures = [];
            $db = Db::getInstance();
            $request = $db->query('SELECT * FROM culture');
                foreach($request->fetchAll() as $cult) {
                $list[] = new Culture($cult['CultureID'], $cult['Name'], $cult['ModifiedDate']);
            }

            return $cultures;
          }


          public static function findById($id) {
            $db = Db::getInstance();
            $id = intval($id);

            $request = $db->prepare('SELECT * FROM culture WHERE CultureID = :id');
            $request->execute(array('id' => $id));
            $culture = $request->fetch();
            
            if ($culture) {
                return new Culture($culture['CultureID'], $culture['Name'], $culture['GroupName'], $culture['ModifiedDate']);
            } else {
                return [];
            }
          }


          public function editCultureById($id, $newName, $newModifiedDate){
            $db = Db::getInstance();
            $id = intval($id);

            $sql = "UPDATE culture SET 'Name' = '$newName', 'ModifiedDate' = '$newModifiedDate') VALUES WHERE CultureID = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Culture updated!";
            } else {
                $rez="Culture not updated!";;
            }
            
            return $rez; 
          }


          public function addCulture($Name, $ModifiedDate){
            $db = Db::getInstance();

            $sql = "INSERT INTO culture (`Name`, `ModifiedDate`) VALUES ('$Name', '$ModifiedDate')";

            if ($db->query($sql) == TRUE){
                $rez="Culture added!";
            } else {
                $rez="Culture not added!";;
            }
            
            return $rez; 
          }


          public static function deleteCultureById($id) {
            $db = Db::getInstance();
            $sql = "DELETE FROM Culture WHERE CultureID = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Culture deleted";
            } else {
                $rez="Deletion error!";;
            }
            
            return $rez; 
        }
    }
?>