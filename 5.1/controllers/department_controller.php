<?php
  class DepartmentController {
    public function index() {
      $departments = Department::getAllDepartments();
      require_once('views/department/index.php');
    }

    public function show() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $department = Department::findById($_GET['id']);
      require_once('views/department/show.php');
    }

	public function deleteDepartmentById() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $department = Department::deleteDepartmentById($_GET['id']);
      require_once('views/department/delete.php');
    }

    public function getAddDepartmentView() {
        require_once('views/department/add.php');
    }

    public function addDepartment() {
        if (!isset($_GET['Name']) || !isset($_GET['GroupName']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $department = Department::addDepartment($_GET['Name'], $_GET['GroupName'], $_GET['ModifiedDate']);
        require_once('views/department/added.php');
    }

    public function getEditDepartmentView() {
        require_once('views/department/edit.php');
    }

    public function editDepartmentById() {
        if (!isset($_GET['DepartmentID']) || !isset($_GET['Name']) || !isset($_GET['GroupName']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $department = Department::editDepartmentById($_GET['DepartmentID'], $_GET['Name'], $_GET['GroupName'], $_GET['ModifiedDate']);
        require_once('views/department/edited.php');
    }
  }
?>