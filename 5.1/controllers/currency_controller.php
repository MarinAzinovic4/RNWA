<?php
  class CurrencyController {
    public function index() {
      $Currencys = Currency::getAllCurrencys();
      require_once('views/currency/index.php');
    }

    public function show() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $Currency = Currency::findById($_GET['id']);
      require_once('views/currency/show.php');
    }

	public function deleteCurrencyById() {
      if (!isset($_GET['id']))
        return call('pages', 'error');

      $Currency = Currency::deleteCurrencyById($_GET['id']);
      require_once('views/currency/delete.php');
    }

    public function getAddCurrencyView() {
        require_once('views/currency/add.php');
    }

    public function addCurrency() {
        if (!isset($_GET['CurrencyCode'], $_GET['Name']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $Currency = Currency::addCurrency($_GET['Name'], $_GET['ModifiedDate']);
        require_once('views/currency/added.php');
    }

    public function getEditCurrencyView() {
        require_once('views/currency/edit.php');
    }

    public function editCurrencyById() {
        if (!isset($_GET['CurrencyCode']) || !isset($_GET['Name']) || !isset($_GET['ModifiedDate']))
            return call('pages', 'error');
        
        $Currency = Currency::editCurrencyById($_GET['CurrencyCode'], $_GET['Name'], $_GET['ModifiedDate']);
        require_once('views/currency/edited.php');
    }
  }
?>