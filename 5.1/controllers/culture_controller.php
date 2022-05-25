<?php
  class CultureController {
    public function index() {
      $Cultures = Culture::getAllCultures();
      require_once('views/culture/index.php');
    }

    public function show() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $Culture = Culture::findById($_GET['id']);
      require_once('views/culture/show.php');
    }

	public function deleteCultureById() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $Culture = Culture::deleteCultureById($_GET['id']);
      require_once('views/culture/delete.php');
    }

    public function getAddCultureView() {
        require_once('views/culture/add.php');
    }

    public function addCulture() {
        if (!isset($_GET['Name']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $Culture = Culture::addCulture($_GET['Name'], $_GET['ModifiedDate']);
        require_once('views/culture/added.php');
    }

    public function getEditCultureView() {
        require_once('views/culture/edit.php');
    }

    public function editCultureById() {
        if (!isset($_GET['CultureID']) || !isset($_GET['Name']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $Culture = Culture::editCultureById($_GET['CultureID'], $_GET['Name'], $_GET['ModifiedDate']);
        require_once('views/culture/edited.php');
    }
  }
?>