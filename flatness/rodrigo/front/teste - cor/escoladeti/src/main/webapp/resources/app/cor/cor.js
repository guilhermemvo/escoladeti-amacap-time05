var moduleCor = angular.module('escoladeti.cor', ['ngRoute']);

moduleCor.config(['$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.
                when('/cadastros/cor/listar', {
            templateUrl: './pages/cor/listarCor.html',
            controller: 'CorController'
        }).
                when('/cadastros/cor/editar/:objeto', {
            templateUrl: './pages/cor/editarCor.html',
            controller: 'CorController'
        });
        $locationProvider.html5Mode(false);
    }]);

moduleCor.controller('CorController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller de Cor!';
        $scope.page = {pageNumber : 1};
        $scope.corAtual = undefined;

        $scope.init = function() {
            $scope.loadCor(1);
        };

        $scope.incluir = function() {            
            window.location.href = "#cadastros/cor/editar/new";
        };

        $scope.initEditarCor = function() {
            if ($routeParams.objeto === 'new') {
                $scope.corAtual = {"nome" : "", id : ""};
            } else {                
                $scope.corDadoId($routeParams.objeto);
            }
        };
        
        $scope.remover = function(index) {
            var cor = $scope.page.list[index];
            if (confirm('Deseja excluir a cor ' + cor.nome + '?')) {
                $scope.removerServidor(cor);
            }
        };
        
        $scope.removerServidor = function(cor) {
            console.log(JSON.stringify(cor));
            $http({
                method: "DELETE",
                url: "./rest/cor/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(cor))
            }).success(function(data, status) {
                $scope.loadCor($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir cor!");
            });
        };

        $scope.loadCor = function(numeroPagina) {        	
            $http({
                method: "GET",
                url: "./rest/cor/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));                
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar cor!");
            });
        };
        
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./rest/cor/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.corAtual)
            }).success(function(data, status) {
                window.location.href = "#cadastros/cor/listar";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir cor!");
            });
        };
        
        $scope.corDadoId = function(id) {        	
        	$http({
        		method: "GET",
        		url: "./rest/cor/carregar/" + id,
        		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        	}).success(function(data, status) {
        		$scope.corAtual = data;
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
