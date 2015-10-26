var moduleEstado = angular.module('escoladeti.estado', ['ngRoute']);

moduleEstado.controller('EstadoController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller dos Estados!';
        $scope.page = {pageNumber : 0};
        $scope.estadoAtual = undefined;
        $scope.search = "";
        $scope.listapaises = [];        

        $scope.init = function() {
            $scope.loadEstados($scope.page.pageNumber);
        };

        $scope.incluir = function() {            
            window.location.href = "#Localizacao/Estado/Novo";
        };

        $scope.initEditarEstado = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.estadoAtual = {
                    id : "",
                    nome : "",
                    codigoIBGE : "",
                    sigla : "",
                    pais: ""
                };
            } else {                
                $scope.estadoDadoId($routeParams.objeto);
            }
            $scope.loadTodosPaises();
        };
        
        $scope.remover = function(index) {
            var estado = $scope.page.list[index];
            if (confirm('Deseja excluir o estado ' + estado.nome + '?')) {
                $scope.removerServidor(estado);
            }
        };
        $scope.loadTodosPaises = function() {
            $http({
                method: "GET",
                url: "./pais/todos",
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(status));
                $scope.listapaises = data;
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Paises!");
            });
        };
        
        $scope.removerServidor = function(estado) {
            console.log(JSON.stringify(estado));
            $http({
                method: "DELETE",
                url: "./unidadefederativa/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(estado))
            }).success(function(data, status) {
                $scope.loadEstados($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir o Estados!");
            });
        };

        $scope.loadEstados = function(numeroPagina) {        	
            $http({
                method: "GET",
                url: "./unidadefederativa/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar Estados!");
            });
        };
        
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./unidadefederativa/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.estadoAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/Estado/";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar Estados!");
            });
        };
        
        $scope.estadoDadoId = function(id) {        	
        	$http({
        		method: "GET",
        		url: "./unidadefederativa/carregar/" + id,
        		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        	}).success(function(data, status) {
        		$scope.estadoAtual = data;
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
