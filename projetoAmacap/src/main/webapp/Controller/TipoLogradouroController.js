var moduleTipoLogradouro = angular.module('escoladeti.tipologradouro', ['ngRoute']);

moduleTipoLogradouro.controller('TipoLogradouroController', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {

        $scope.message = 'Controller dos Tipos de Logradouro!';
        $scope.page = {pageNumber : 0};
        $scope.tipoLogradouroAtual = undefined;
        $scope.search = "";

        $scope.init = function() {
            $scope.loadtiposLogradouro($scope.page.pageNumber);
        };

        $scope.incluir = function() {            
            window.location.href = "#Localizacao/TipoLogradouro/Novo";
        };

        $scope.initEditarTipoLogradouro = function() {
            if ($routeParams.objeto === 'Novo') {
                $scope.tipoLogradouroAtual = {
                    descricao : ""
                };
            } else {                
                $scope.tipoLogradouroDadoId($routeParams.objeto);
            }
        };
        
        $scope.remover = function(index) {
            var tipoLogradouro = $scope.page.list[index];
            if (confirm('Deseja excluir o tipo logradouro ' + tipoLogradouro.descricao + '?')) {
                $scope.removerServidor(tipoLogradouro);
            }
        };
        
        $scope.removerServidor = function(tipoLogradouro) {
            console.log(JSON.stringify(tipoLogradouro));
            $http({
                method: "DELETE",
                url: "./tipologradouro/remover",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify(angular.copy(tipoLogradouro))
            }).success(function(data, status) {
                $scope.loadtiposLogradouro($scope.page.pageNumber);
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao excluir o tipo de logradouro!");
            });
        };

        $scope.loadtiposLogradouro = function(numeroPagina) {        	
            $http({
                method: "GET",
                url: "./tipologradouro/listar/pag/" + numeroPagina,
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data, status) {
                console.log(JSON.stringify(data));
                $scope.page = data;
                console.log(JSON.stringify(status));                
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao buscar os tipos de logradouro!");
            });
        };
        
        $scope.salvar = function() {
            $http({
                method: "POST",
                url: "./tipologradouro/salvar",
                headers: {'Content-Type': 'application/json; charset=UTF-8'},
                data: JSON.stringify($scope.tipoLogradouroAtual)
            }).success(function(data, status) {
                window.location.href = "#Localizacao/TipoLogradouro/";
                console.log(JSON.stringify(status));
            }).error(function(data, status) {
                console.log(JSON.stringify(data));
                console.log(JSON.stringify(status));
                alert("Erro ao Salvar o tipo de logradouro!");
            });
        };
        
        $scope.tipoLogradouroDadoId = function(id) {        	
        	$http({
        		method: "GET",
        		url: "./tipologradouro/carregar/" + id,
        		headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}
        	}).success(function(data, status) {
        		$scope.tipoLogradouroAtual = data;
        	}).error(function(data, status) {
        		$("<div></div>")
        		.css({overflow : "auto"})
        		.html(data)
        		.dialog({
        			autoOpen: true,
        			title: 'Erro ao carregar o tipo de logradouro ' + id,
        			width: 800,
        			height: 450,
        			modal: true
        		});
        	});
        };
        
    }
]);
