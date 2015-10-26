var moduleEndereco = angular.module('escoladeti.endereco', ['ngRoute']);

moduleEndereco.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.
                when('/cadastros/endereco/listar', {
            templateUrl: './pages/endereco/listarEndereco.html',
            controller: 'CorController'
        }).
                when('/cadastros/endereco/editar/:objeto', {
            templateUrl: './pages/endereco/editarEndereco.html',
            controller: 'EnderecoController'
        });
        $locationProvider.html5Mode(false);
    }]);

moduleEndereco.controller('EnderecoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller de Endereco!';
        $scope.page = {pageNumber : 1};
        $scope.enderecoAtual = undefined;

        $scope.init = function() {
            $scope.loadCor(1);
        };

        $scope.incluir = function() {            
            window.location.href = "#cadastros/endereco/editar/new";
        };

        $scope.initEditarEndereco = function() {
            if ($routeParams.objeto === 'new') {
                $scope.enderecoAtual = {"nome" : "", id : ""};
            } else {                
                $scope.enderecoDadoId($routeParams.objeto);
            }
        };
        
        $scope.remover = function(index) {
            var cor = $scope.page.list[index];
            if (confirm('Deseja excluir a endereco ' + cor.nome + '?')) {
                $scope.removerServidor(cor);
            }
        };
        
        $scope.removerServidor = function(cor) {
            console.log(JSON.stringify(cor));
            $http({
                method: "DELETE",
                url: "./rest/endereco/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(cor))
            }).success(function(data, status) {
                $scope.loadEndereco($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir endereco!");
            });
        };

        $scope.loadCor = function(numeroPagina) {        	
            $http({
                method: "GET",
                url: "./rest/endereco/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));                
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar endereco!");
            });
        };
        
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./rest/endereco/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.enderecoAtual)
            }).success(function(data, status) {
                window.location.href = "#cadastros/endereco/listar";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao salvar endereco!");
            });
        };
        
        $scope.enderecoDadoId = function(id) {        	
        	$http({
        		method: "GET",
        		url: "./rest/endereco/carregar/" + id,
        		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        	}).success(function(data, status) {
        		$scope.enderecoAtual = data;
        	}).error(function(data, status) {
        		$("<div></div>")
        		.css({overflow : "auto"})
        		.html(data)
        		.dialog({
        			autoOpen: true,
        			title: 'Erro ao carregar questão ' + id,
        			width: 800,
        			height: 450,
        			modal: true
        		});
        	});
        };
        
    }
]);
