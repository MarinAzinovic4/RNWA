<?php
  function call($controller, $action) {
    require_once('controllers/' . $controller . '_controller.php');

    switch($controller) {
      case 'pages':
      $controller = new PagesController();
      break;
	  case 'department':
      require_once('models/department.php');
		  $controller = new DepartmentController();
      break;
	  case 'currency':
      require_once('models/currency.php');
		  $controller = new CurrencyController();
      break;
    case 'culture':
      require_once('models/culture.php');
      $controller = new CultureController();
      break;
    }

    $controller->{ $action }();
  }

  $controllers = array('pages' 		=> ['home', 'error'],
            'department' 		=> ['index', 'show', 'findById', 'deleteDepartmentById', 'getAddDepartmentView', 'addDepartment', 'getEditDepartmentView', 'editDepartment'],
					  'currency' => ['index', 'show', 'findById', 'deleteCurrencyById', 'getAddCurrencyView', 'addCurrency', 'getEditCurrencyView', 'editCurrency'],
					  'culture' 	=> ['index', 'show', 'findById', 'deleteCultureId', 'getAddCultureView', 'addCulture', 'getEditCultureView', 'editCulture']);

  if (array_key_exists($controller, $controllers)) {
    if (in_array($action, $controllers[$controller])) {
      call($controller, $action);
    } else {
      call('pages', 'error');
    }
  } else {
    call('pages', 'error');
  }
?>