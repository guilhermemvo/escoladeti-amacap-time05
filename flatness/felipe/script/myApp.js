var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(function($routeProvider) {
		
		$routeProvider

			// route for the home page
			.when('/Localizacao/Distrito', {
				templateUrl : 'View/listarDistrito.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Distrito/Novo', {
				templateUrl : 'View/editarDistrito.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Cidade', {
				templateUrl : 'View/listarCidades.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Cidade/Novo', {
				templateUrl : 'View/editarCidade.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Estado', {
				templateUrl : 'View/listarEstados.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Estado/Novo', {
				templateUrl : 'View/editarEstado.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Pais', {
				templateUrl : 'View/listarPaises.html'
				//controller  : 'localizacaoController'
			})
			.when('/Localizacao/Pais/Novo', {
				templateUrl : 'View/editarPais.html'
				//controller  : 'localizacaoController'
			});
});
 

myApp.controller('mainController', function($scope) {
		// create a message to display in our view
		$scope.message = 'Everyone come and see how good I look!';
});

myApp.controller('localizacaoController', function($scope) {
		// create a message to display in our view
	$scope.message = 'Everyone come and see how good I look!';
	
	$scope.pais = [{nome: "Brasil", sigla: "BR"}];
	//$scope.pais = [];
	$scope.addNovoPais = function($paises){
		var r = myApp.copy($paises);
		$scope.paises.push(r);
	}
});


