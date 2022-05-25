<?php
    class Department  {
        private $DepartmentID;
        private $Name;
        private $GroupName;
        private $ModifiedDate;


        public function __construct($DepartmentID = null, $Name, $GroupName, $ModifiedDate) {
            $this->Name = $Name;
            $this->GroupName = $GroupName;
            $this->ModifiedDate = $ModifiedDate;
        }


        public function getDepartmentId() {
            return $this->DepartmentID;
        }

        public function getName() {
            return $this->Name;
        }
        public function setName($name) {
            return $this->Name = $name;
        }

        public function geGroupName() {
            return $this->GroupName;
        }
        public function setGroupName($groupName) {
            return $this->GroupName = $GroupName;
        }

        public function getModifiedDate() {
            return $this->ModifiedDate;
        }
        public function setModifiedDate($modifiedDate) {
            return $this->ModifiedDate = $modifiedDate;
        }


        public static function getAllDepartments() {
            $departments = [];
            $db = Db::getInstance();
            $request = $db->query('SELECT * FROM department');
                foreach($request->fetchAll() as $dept) {
                $list[] = new Department($dept['DepartmentID'], $dept['Name'], $dept['GroupName'], $dept['ModifiedDate']);
            }

            return $departments;
          }


          public static function findById($id) {
            $db = Db::getInstance();
            $id = intval($id);

            $request = $db->prepare('SELECT * FROM department WHERE DepartmentID = :id');
            $request->execute(array('id' => $id));
            $department = $request->fetch();
            
            if ($department) {
                return new Department($department['DepartmentID'], $department['Name'], $department['GroupName'], $department['ModifiedDate']);
            } else {
                return [];
            }
          }


          public function editDepartmentById($id, $newName, $newGroupName, $newModifiedDate){
            $db = Db::getInstance();
            $id = intval($id);

            $sql = "UPDATE department SET 'Name' = '$newName', 'GroupName' = '$newGroupname', 'ModifiedDate' = '$newModifiedDate') VALUES WHERE DepartmentID = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Department updated!";
            } else {
                $rez="Department not updated!";;
            }
            
            return $rez; 
          }


          public function addDepartment($Name, $GroupName, $ModifiedDate){
            $db = Db::getInstance();

            $sql = "INSERT INTO department (`Name`, `GroupName`, `ModifiedDate`) VALUES ('$Name', '$GroupName', '$ModifiedDate')";

            if ($db->query($sql) == TRUE){
                $rez="Department added!";
            } else {
                $rez="Department not added!";;
            }
            
            return $rez; 
          }


          public static function deleteDepartmentById($id) {
            $db = Db::getInstance();
            $sql = "DELETE FROM department WHERE DepartmentID = '$id'";

            if ($db->query($sql) == TRUE){
                $rez="Department deleted";
            } else {
                $rez="Deletion error!";;
            }
            
            return $rez; 
        }
    }
?>