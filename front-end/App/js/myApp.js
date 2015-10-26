var myApp = angular.module('myApp', ['ngRoute']);

myApp.controller('MenuController', function ($scope) {
	$scope.message = 'teste !';

});

myApp.config(['$routeProvider', '$locationProvider' ,
	function ($routeProvider, $locationProvider) {
		//$locationProvider.html5Mode(true);

		$routeProvider

		//Localização

		.when('/Localizacao/Pais', {
			templateUrl: './App/View/Localizacao/Pais/listarPais.html'
			//controller: 'Ctrl'
		})
		.when('/Localizacao/Pais/Novo', {
			templateUrl: './App/View/Localizacao/Pais/editarPais.html'
		})

		.when('/Localizacao/Cidade', {
			templateUrl: './App/View/Localizacao/Cidade/listarCidade.html'
		})
		.when('/Localizacao/Cidade/Novo', {
			templateUrl: './App/View/Localizacao/Cidade/editarCidade.html'
		})

		.when('/Localizacao/Estado', {
			templateUrl: './App/View/Localizacao/Estado/listarEstado.html'	
		})
		.when('/Localizacao/Estado/Novo', {
			templateUrl: './App/View/Localizacao/Estado/editarEstado.html'	
		})
		.when('/Localizacao/Distrito', {
			templateUrl: './App/View/Localizacao/Distrito/listarDistrito.html'
		})
		.when('/Localizacao/Distrito/Novo', {
			templateUrl: './App/View/Localizacao/Distrito/editarDistrito.html'	
		})


		//Configuração
		.when('/Configuracao/Permissoes',{
			templateUrl: './App/View/Configuracao/Permissoes/listarPermissao.html'
		})
			.when('/Configuracao/Permissoes/Novo',{
			templateUrl: './App/View/Configuracao/Permissoes/editarPermissao.html'
		})

		//Pessoa
		.when('/Pessoa/Cliente',{
			templateUrl: './App/View/Pessoa/Cliente/listarCliente.html'
		})
		.when('/Pessoa/Cliente/Novo',{
			templateUrl: './App/View/Pessoa/Cliente/editarCliente.html'
		})

		.when('/Pessoa/Usuario',{
			templateUrl: './App/View/Pessoa/Usuario/listarUsuario.html'
		})
		.when('/Pessoa/Usuario/Novo',{
			templateUrl: './App/View/Pessoa/Usuario/editarUsuario.html'
		})
	
		
		.otherwise({ redirectTo: '/' })


}]);